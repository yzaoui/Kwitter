package kwitter.backend

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.routing.Route

fun Route.login() {
    get<Login> {
        call.respond(HttpStatusCode.NotImplemented)
    }
}
