package kwitter.route

import io.ktor.application.call
import io.ktor.locations.Location
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
import kwitter.href

@Location("/new-kweet")
class NewKweetLocation

fun Route.newKweet() {
    post<NewKweetLocation> {
        val loggedInUser = call.sessions.get<KwitterSession>()?.username?.let { UserRepository.get(it) }
        if (loggedInUser == null) {
            call.respondRedirect(href(LoginLocation()))
            return@post
        }

        val params = call.receiveParameters()
        val newKweetText = params["new-kweet-text"]

        // In case of invalid submission, return to homepage
        if (newKweetText == null || newKweetText.length !in 1..MAX_KWEET_LENGTH) {
            call.respondRedirect(href(IndexLocation()))
            return@post
        }

        val transformedKweetText = newKweetText.let {
            // Replace \r\n and \r with \n
            Regex("\\r\\n|\\r").replace(it, "\n")
        }.let {
            // Replace \n\n(\n)* with \n\n
            Regex("\\n{3,}").replace(it, "\n\n")
        }

        KweetRepository.create(loggedInUser.username, transformedKweetText)
        call.respondRedirect(href(IndexLocation()))
    }
}
