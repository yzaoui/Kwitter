package kwitter

import io.ktor.application.call
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.routing.Route
import kwitter.freemarker.signupFTL

fun Route.signUp() {
    get<SignUp> {
        call.respond(signupFTL(signUpHref = SignUp.path))
    }
    post<SignUp> {
        val params = call.receiveParameters()
        val errorMessages = mutableListOf<String>()

        val username = params["username"]
        val password = params["password"] //TODO: Handle password better
        val displayName = params["displayName"]
        val email = params["email"]

        if (username == null) errorMessages.add("Missing username.")
        if (password == null) errorMessages.add("Missing password.")
        if (displayName == null) errorMessages.add("Missing display name.")
        if (email == null) errorMessages.add("Missing email.")

        if (errorMessages.isEmpty()) {
            call.respond(signupFTL(
                signUpHref = SignUp.path,
                errorMessage = "Sign up isn't implemented yet! :("
            ))
        } else {
            call.respond(signupFTL(
                signUpHref = SignUp.path,
                errorMessage = errorMessages.joinToString("<br>")
            ))
        }
    }
}
