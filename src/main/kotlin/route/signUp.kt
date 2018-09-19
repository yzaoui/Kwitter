package com.bitwiserain.kwitter.route

import at.favre.lib.crypto.bcrypt.BCrypt
import com.bitwiserain.kwitter.KwitterSession
import com.bitwiserain.kwitter.USERNAME_REGEX
import com.bitwiserain.kwitter.data.UserRepository
import com.bitwiserain.kwitter.domain.usecase.UsernameAvailability
import com.bitwiserain.kwitter.freemarker.signupFTL
import com.bitwiserain.kwitter.freemarker.signupFTLError
import com.bitwiserain.kwitter.href
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

@Location("/signup")
class SignUpLocation

fun Route.signUp(usernameAvailability: UsernameAvailability) {
    get<SignUpLocation> {
        call.respond(signupFTL(href(SignUpLocation())))
    }
    post<SignUpLocation> {
        val params = call.receiveParameters()
        val errorMessages = mutableListOf<String>()

        val usernameParam = params["username"]
        val usernameValid = usernameParam?.matches(USERNAME_REGEX.toRegex()) ?: false
        val usernameAvailable = usernameParam?.let { usernameAvailability.check(it) } ?: false
        val passwordParam = params["password"] //TODO: Encourage stronger password
        val displayNameParam = params["displayName"] //TODO: Validate display name
        val emailParam = params["email"] //TODO: Validate email

        if (usernameParam != null && usernameValid && usernameAvailable && passwordParam != null && displayNameParam != null && emailParam != null) {
            val userId = UserRepository.create(
                username = usernameParam,
                passwordHash = BCrypt.withDefaults().hash(12, passwordParam.toByteArray()),
                displayName = displayNameParam,
                email = emailParam,
                profilePictureURL = "/assets/images/default.png"
            )
            call.sessions.set(KwitterSession(userId))
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
