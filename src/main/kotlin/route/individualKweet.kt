package kwitter.route

import io.ktor.application.call
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
import kwitter.location.FollowLocation
import kwitter.location.IndexLocation
import kwitter.location.IndividualKweetLocation
import kwitter.location.ProfileLocation

fun Route.individualKweet() {
    get<IndividualKweetLocation> {
        val author = UserRepository.get(it.username)
        val kweet = KweetRepository.get(it.kweetId)

        if (!(author != null && kweet != null && kweet.username == author.username)) {
            call.respondRedirect(IndexLocation.path)
            return@get
        }

        val htmlKweet = kweet.toHTMLKweet(UserRepository)

        val loggedInUser = call.sessions.get<KwitterSession>()?.username?.let { UserRepository.get(it) }

        if (loggedInUser != null) {
            when {
                // One's own kweet
                loggedInUser.username == author.username -> call.respond(individualKweetFTL(htmlKweet, loggedInUser, ProfileLocation.createPath(loggedInUser.username)))
                // Kweet of someone one follows
                CheckFollow.follows(loggedInUser.username, author.username) -> call.respond(individualKweetFTLFollowing(htmlKweet, "", loggedInUser, ProfileLocation.createPath(loggedInUser.username)))
                // Kweet of someone one does not follow
                else -> call.respond(individualKweetFTLNotFollowing(htmlKweet, FollowLocation.createPath(author.username), loggedInUser, ProfileLocation.createPath(loggedInUser.username)))
            }
        } else {
            call.respond(individualKweetFTLGuest(htmlKweet))
        }
    }
}
