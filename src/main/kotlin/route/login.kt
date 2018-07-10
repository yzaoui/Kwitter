package kwitter.route

import io.ktor.application.call
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.routing.Route
import io.ktor.sessions.sessions
import io.ktor.sessions.set
import kwitter.KwitterSession
import kwitter.data.UserRepository
import kwitter.freemarker.loginFTL
import kwitter.location.IndexLocation
import kwitter.location.LoginLocation

fun Route.login() {
    get<LoginLocation> {
        call.respond(loginFTL(loginHref = LoginLocation.PATH))
    }
    post<LoginLocation> {
        val params = call.receiveParameters()
        val errorMessages = mutableListOf<String>()

        val usernameParam = params["username"]
        val passwordParam = params["password"]

        if (usernameParam != null && passwordParam != null) {
            val user = UserRepository.get(usernameParam)
            if (passwordParam == user?.passwordHash) {
                call.sessions.set(KwitterSession(user.username))
                call.respondRedirect(IndexLocation.path)
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
    loginHref = LoginLocation.PATH,
    errorMessage = errorMessage
)