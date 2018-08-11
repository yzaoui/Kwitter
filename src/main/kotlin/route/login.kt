package kwitter.route

import at.favre.lib.crypto.bcrypt.BCrypt
import io.ktor.application.call
import io.ktor.locations.Location
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
import kwitter.href

@Location("/login")
class LoginLocation

fun Route.login() {
    get<LoginLocation> {
        call.respond(loginFTL(loginHref = href(LoginLocation())))
    }
    post<LoginLocation> {
        val params = call.receiveParameters()
        val errorMessages = mutableListOf<String>()

        val usernameParam = params["username"]
        val passwordParam = params["password"]

        if (usernameParam != null && passwordParam != null) {
            val user = UserRepository.get(usernameParam)
            if (user != null && BCrypt.verifyer().verify(passwordParam.toByteArray(), user.passwordHash).verified) {
                call.sessions.set(KwitterSession(user.username))
                call.respondRedirect(href(IndexLocation()))
            } else {
                call.respond(loginPageWithError(
                    loginURL = href(LoginLocation()),
                    errorMessage = "Incorrect username and/or password."
                ))
            }
        } else {
            if (usernameParam == null) errorMessages.add("Missing username.")
            if (passwordParam == null) errorMessages.add("Missing password.")

            call.respond(loginPageWithError(
                loginURL = href(LoginLocation()),
                errorMessage = errorMessages.joinToString("<br>")
            ))
        }
    }
}

private fun loginPageWithError(loginURL: String, errorMessage: String) = loginFTL(
    loginHref = loginURL,
    errorMessage = errorMessage
)
