package kwitter

import io.ktor.application.call
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.routing.Route
import io.ktor.sessions.sessions
import io.ktor.sessions.set
import kwitter.data.UserRepository
import kwitter.data.model.User
import kwitter.freemarker.signupFTL

fun Route.signUp() {
    get<SignUp> {
        call.respond(signupFTL(signUpHref = SignUp.path))
    }
    post<SignUp> {
        val params = call.receiveParameters()
        val errorMessages = mutableListOf<String>()

        val usernameParam = params["username"]
        val passwordParam = params["password"] //TODO: Handle password better
        val displayNameParam = params["displayName"]
        val emailParam = params["email"]

        if (usernameParam != null && passwordParam != null && displayNameParam != null && emailParam != null) {
            val newUser = User(
                username = usernameParam,
                passwordHash = passwordParam,
                displayName = displayNameParam,
                email = emailParam
            )
            UserRepository.create(newUser)
            call.sessions.set(KwitterSession(newUser.username))
            call.respondRedirect(Index.path)
        } else {
            if (usernameParam == null) errorMessages.add("Missing username.")
            if (passwordParam == null) errorMessages.add("Missing password.")
            if (displayNameParam == null) errorMessages.add("Missing display name.")
            if (emailParam == null) errorMessages.add("Missing email.")

            call.respond(signupFTL(
                signUpHref = SignUp.path,
                errorMessage = errorMessages.joinToString("<br>")
            ))
        }
    }
}
