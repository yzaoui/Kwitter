package kwitter.route

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
import kwitter.USERNAME_REGEX
import kwitter.data.UserRepository
import kwitter.data.model.User
import kwitter.freemarker.signupFTL
import kwitter.freemarker.signupFTLError
import kwitter.href

@Location("/signup")
class SignUpLocation

fun Route.signUp() {
    get<SignUpLocation> {
        call.respond(signupFTL(href(SignUpLocation())))
    }
    post<SignUpLocation> {
        val params = call.receiveParameters()
        val errorMessages = mutableListOf<String>()

        val usernameParam = params["username"]
        val usernameValid = usernameParam?.matches(USERNAME_REGEX.toRegex()) ?: false
        val passwordParam = params["password"] //TODO: Handle password better
        val displayNameParam = params["displayName"]
        val emailParam = params["email"]

        if (usernameParam != null && usernameValid && passwordParam != null && displayNameParam != null && emailParam != null) {
            val newUser = User(
                username = usernameParam,
                passwordHash = passwordParam,
                displayName = displayNameParam,
                email = emailParam,
                profilePictureURL = "/assets/images/default.png"
            )
            UserRepository.create(newUser)
            call.sessions.set(KwitterSession(newUser.username))
            call.respondRedirect(href(IndexLocation()))
        } else {
            if (usernameParam == null) errorMessages.add("Missing username.")
            else if (!usernameValid) errorMessages.add("Username must consist of 1-15 letters, numbers, and/or underscores.")
            if (passwordParam == null) errorMessages.add("Missing password.")
            if (displayNameParam == null) errorMessages.add("Missing display name.")
            if (emailParam == null) errorMessages.add("Missing email.")

            call.respond(signupFTLError(
                errorMessage = errorMessages.joinToString("<br>"),
                signUpURL = href(SignUpLocation())
            ))
        }
    }
}
