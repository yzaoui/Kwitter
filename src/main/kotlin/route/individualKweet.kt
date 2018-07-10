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
import kwitter.freemarker.individualKweetFTL
import kwitter.location.IndexLocation
import kwitter.location.IndividualKweetLocation
import kwitter.location.ProfileLocation

fun Route.individualKweet() {
    get<IndividualKweetLocation> {
        val user = UserRepository.get(it.username)
        val kweet = KweetRepository.get(it.kweetId)

        if (!(user != null && kweet != null && kweet.username == user.username)) {
            call.respondRedirect(IndexLocation.path)
            return@get
        }

        val htmlKweet = kweet.toHTMLKweet(UserRepository)

        val loggedInUser = call.sessions.get<KwitterSession>()?.username?.let { UserRepository.get(it) }

        if (loggedInUser != null) {
            call.respond(individualKweetFTL(htmlKweet, loggedInUser, ProfileLocation.createPath(loggedInUser.username)))
        } else {
            call.respond(individualKweetFTL(htmlKweet))
        }
    }
}
