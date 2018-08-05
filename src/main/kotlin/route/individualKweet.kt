package kwitter.route

import io.ktor.application.call
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.routing.Route
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import kwitter.KwitterSession
import kwitter.data.KweetRepository
import kwitter.data.UserRepository
import kwitter.domain.usecase.CheckFollow
import kwitter.freemarker.individualKweetFTL
import kwitter.freemarker.individualKweetFTLFollowing
import kwitter.freemarker.individualKweetFTLGuest
import kwitter.freemarker.individualKweetFTLNotFollowing
import kwitter.href

@Location("/{username}/kweet/{kweetId}")
class IndividualKweetLocation(val username: String, val kweetId: String)

fun Route.individualKweet() {
    get<IndividualKweetLocation> {
        val author = UserRepository.get(it.username)
        val kweet = KweetRepository.get(it.kweetId)

        if (!(author != null && kweet != null && kweet.username == author.username)) {
            call.respondRedirect(href(IndexLocation()))
            return@get
        }

        val htmlKweet = kweet.toHTMLKweet(UserRepository, { username, kweetId -> href(IndividualKweetLocation(username, kweetId)) }, { username -> href(ProfileLocation(username)) })

        val loggedInUser = call.sessions.get<KwitterSession>()?.username?.let { UserRepository.get(it) }

        if (loggedInUser != null) {
            when {
                // One's own kweet
                loggedInUser.username == author.username -> call.respond(individualKweetFTL(
                    htmlKweet = htmlKweet,
                    loggedInUser = loggedInUser,
                    loggedInUserURL = href(ProfileLocation(loggedInUser.username)),
                    logoutURL = href(LogoutLocation()),
                    generateAvatarURL = href(GenerateAvatarLocation())
                ))
                // Kweet of someone one follows
                CheckFollow.follows(loggedInUser.username, author.username) -> call.respond(individualKweetFTLFollowing(
                    htmlKweet = htmlKweet,
                    unfollowURL = href(UnfollowLocation(author.username)),
                    loggedInUser = loggedInUser,
                    loggedInUserURL = href(ProfileLocation(loggedInUser.username)),
                    logoutURL = href(LogoutLocation()),
                    generateAvatarURL = href(GenerateAvatarLocation())
                ))
                // Kweet of someone one does not follow
                else -> call.respond(individualKweetFTLNotFollowing(
                    htmlKweet = htmlKweet,
                    followURL = href(FollowLocation(author.username)),
                    loggedInUser = loggedInUser,
                    loggedInUserURL = href(ProfileLocation(loggedInUser.username)),
                    logoutURL = href(LogoutLocation()),
                    generateAvatarURL = href(GenerateAvatarLocation())
                ))
            }
        } else {
            call.respond(individualKweetFTLGuest(htmlKweet, href(LoginLocation())))
        }
    }
}
