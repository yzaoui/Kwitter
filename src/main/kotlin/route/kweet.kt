package kwitter.route

import io.ktor.application.call
import io.ktor.locations.post
import io.ktor.request.receiveParameters
import io.ktor.response.respondRedirect
import io.ktor.routing.Route
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import kwitter.KwitterSession
import kwitter.MAX_KWEET_LENGTH
import kwitter.data.KweetRepository
import kwitter.data.UserRepository
import kwitter.location.IndexLocation
import kwitter.location.KweetLocation
import kwitter.location.LoginLocation

fun Route.kweet() {
    post<KweetLocation> {
        val user = call.sessions.get<KwitterSession>()?.username?.let { UserRepository.get(it) }
        if (user == null) {
            call.respondRedirect(LoginLocation.PATH)
            return@post
        }

        val params = call.receiveParameters()
        val newKweetText = params["new-kweet-text"]

        // In case of invalid submission, return to homepage
        if (newKweetText == null || newKweetText.length !in 1..MAX_KWEET_LENGTH) {
            call.respondRedirect(IndexLocation.PATH)
            return@post
        }

        val transformedKweetText = newKweetText.let {
            // Replace \r\n and \r with \n
            Regex("\\r\\n|\\r").replace(it, "\n")
        }.let {
            // Replace \n\n(\n)* with \n\n
            Regex("\\n{3,}").replace(it, "\n\n")
        }

        KweetRepository.create(user.username, transformedKweetText)
        call.respondRedirect(IndexLocation.PATH)
    }
}
