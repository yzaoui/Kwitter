package kwitter.backend

import freemarker.loginFTL
import io.ktor.application.call
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.routing.Route

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
