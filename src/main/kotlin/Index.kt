package kwitter

import io.ktor.application.call
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.routing.Route
import kwitter.freemarker.welcomeFTL

fun Route.index() {
    get<Index> {
        call.respond(welcomeFTL(
            signUpHref = SignUp.path,
            loginHref = Login.path
        ))
    }
}
