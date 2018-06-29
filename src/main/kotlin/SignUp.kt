package kwitter

import freemarker.signupFTL
import io.ktor.application.call
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.routing.Route

fun Route.signUp() {
    get<SignUp> {
        call.respond(signupFTL(signUpHref = SignUp.path))
    }
    post<SignUp> {
        val params = call.receiveParameters()
        call.respond(
            signupFTL(
            signUpHref = SignUp.path,
            errorMessage = "Sign up isn't implemented yet! :("
        )
        )
    }
}
