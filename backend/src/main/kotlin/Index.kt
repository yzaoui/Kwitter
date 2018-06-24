package kwitter.backend

import io.ktor.application.application
import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.locations.get
import io.ktor.locations.locations
import io.ktor.response.respond
import io.ktor.routing.Route

fun Route.index() {
    get<Index> {
        call.respond(FreeMarkerContent("welcome.ftl", mapOf(
            "signUpHref" to application.locations.href(SignUp()),
            "loginHref" to application.locations.href(Login())
        )))
    }
}
