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
import kwitter.USERNAME_REGEX
import kwitter.data.UserRepository
import kwitter.data.model.User
import kwitter.domain.usecase.UsernameAvailability
import kwitter.freemarker.signupFTL
import kwitter.freemarker.signupFTLError
import kwitter.href

@Location("/signup")
class SignUpLocation

fun Route.signUp(usernameAvailability: UsernameAvailability) {
    get<SignUpLocation> {
        call.respond(signupFTL(href(SignUpLocation())))
    }
    post<SignUpLocation> { signUpLocation ->
        val params = call.receiveParameters()
        val errorMessages = mutableListOf<String>()

        val usernameParam = params["username"]
        val usernameValid = usernameParam?.matches(USERNAME_REGEX.toRegex()) ?: false
        val usernameAvailable = usernameParam?.let { usernameAvailability.check(it) } ?: false
        val passwordParam = params["password"]
        val displayNameParam = params["displayName"]
        val emailParam = params["email"]

        if (usernameParam != null && usernameValid && usernameAvailable && passwordParam != null && displayNameParam != null && emailParam != null) {
            val newUser = User(
                username = usernameParam,
                passwordHash = BCrypt.withDefaults().hash(12, passwordParam.toByteArray()),
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
            else if (!usernameAvailable) errorMessages.add("Username is unavailable.")
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
