package com.bitwiserain.kwitter.route

import com.bitwiserain.kwitter.KwitterSession
import com.bitwiserain.kwitter.data.KweetRepository
import com.bitwiserain.kwitter.data.UserRepository
import com.bitwiserain.kwitter.domain.usecase.CheckFollow
import com.bitwiserain.kwitter.freemarker.individualKweetFTL
import com.bitwiserain.kwitter.freemarker.individualKweetFTLFollowing
import com.bitwiserain.kwitter.freemarker.individualKweetFTLGuest
import com.bitwiserain.kwitter.freemarker.individualKweetFTLNotFollowing
import com.bitwiserain.kwitter.href
import io.ktor.application.call
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.routing.Route
import io.ktor.sessions.get
import io.ktor.sessions.sessions

@Location("/{username}/kweet/{kweetId}")
class IndividualKweetLocation(val username: String, val kweetId: String)

fun Route.individualKweet(checkFollow: CheckFollow) {
    get<IndividualKweetLocation> {
        val author = UserRepository.getByUsername(it.username)
        val kweet = KweetRepository.get(it.kweetId.toInt())

        if (!(author != null && kweet != null && kweet.authorId == author.id)) {
            call.respondRedirect(href(IndexLocation()))
            return@get
        }

        val htmlKweet = kweet.toHTMLKweet(UserRepository, { username, kweetId -> href(IndividualKweetLocation(username, kweetId)) }, { username -> href(ProfileLocation(username)) })

        val loggedInUser = call.sessions.get<KwitterSession>()?.let { UserRepository.get(it.userId) }

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
                checkFollow.follows(loggedInUser.username, author.username) -> call.respond(individualKweetFTLFollowing(
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
