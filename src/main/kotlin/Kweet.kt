package kwitter

import io.ktor.application.call
import io.ktor.locations.post
import io.ktor.request.receiveParameters
import io.ktor.response.respondRedirect
import io.ktor.routing.Route
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import kwitter.data.KweetRepository

fun Route.kweet() {
    post<KweetLocation> {
        val session = call.sessions.get<KwitterSession>()
        if (session == null) {
            call.respondRedirect(Login.path)
            return@post
        }

        val params = call.receiveParameters()
        val newKweetText = params["new-kweet-text"]

        // In case of invalid submission, return to homepage
        if (newKweetText == null || newKweetText.length !in 1..MAX_KWEET_LENGTH) {
            call.respondRedirect(Index.path)
            return@post
        }

        KweetRepository.create(session.username, newKweetText)
        call.respondRedirect(Index.path)
    }
}
