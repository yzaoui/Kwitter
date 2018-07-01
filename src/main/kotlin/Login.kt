package kwitter

import io.ktor.application.call
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.routing.Route
import kwitter.freemarker.loginFTL

fun Route.login() {
    get<Login> {
        call.respond(loginFTL(loginHref = Login.path))
    }
    post<Login> {
        val params = call.receiveParameters()
        call.respond(loginFTL(
            loginHref = Login.path,
            errorMessage = "Login isn't implemented yet! :("
        ))
    }
}
