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
import kwitter.freemarker.loginFTL

fun Route.login() {
    get<Login> {
        call.respond(loginFTL(loginHref = Login.path))
    }
    post<Login> {
        val params = call.receiveParameters()
        val errorMessages = mutableListOf<String>()

        val usernameParam = params["username"]
        val passwordParam = params["password"]

        if (usernameParam != null && passwordParam != null) {
            val user = UserRepository.get(usernameParam)
            if (passwordParam == user?.passwordHash) {
                call.sessions.set(KwitterSession(user.username))
                call.respondRedirect(Index.path)
            } else {
                call.respond(loginPageWithError("Incorrect username and/or password."))
            }
        } else {
            if (usernameParam == null) errorMessages.add("Missing username.")
            if (passwordParam == null) errorMessages.add("Missing password.")

            call.respond(loginPageWithError(errorMessage = errorMessages.joinToString("<br>")))
        }
    }
}

private fun loginPageWithError(errorMessage: String) = loginFTL(
    loginHref = Login.path,
    errorMessage = errorMessage
)
