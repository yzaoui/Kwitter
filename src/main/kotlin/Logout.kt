package kwitter

import io.ktor.application.call
import io.ktor.locations.post
import io.ktor.response.respondRedirect
import io.ktor.routing.Route
import io.ktor.sessions.clear
import io.ktor.sessions.sessions

fun Route.logout() {
    post<Logout> {
        call.sessions.clear<KwitterSession>()
        call.respondRedirect(Index.path)
    }
}
