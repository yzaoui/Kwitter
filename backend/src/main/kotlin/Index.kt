package kwitter.backend

import io.ktor.application.call
import io.ktor.locations.get
import io.ktor.response.respondText
import io.ktor.routing.Route

fun Route.index() {
    get<Index> {
        call.respondText("Hello, World!")
    }
}
