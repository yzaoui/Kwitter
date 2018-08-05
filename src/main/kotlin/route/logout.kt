package kwitter.route

import io.ktor.application.call
import io.ktor.locations.Location
import io.ktor.locations.post
import io.ktor.response.respondRedirect
import io.ktor.routing.Route
import io.ktor.sessions.clear
import io.ktor.sessions.sessions
import kwitter.KwitterSession
import kwitter.href

@Location("/logout")
class LogoutLocation

fun Route.logout() {
    post<LogoutLocation> {
        call.sessions.clear<KwitterSession>()
        call.respondRedirect(href(IndexLocation()))
    }
}
