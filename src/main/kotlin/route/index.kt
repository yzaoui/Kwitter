package kwitter.route

import io.ktor.application.call
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import kwitter.KwitterSession
import kwitter.MAX_KWEET_LENGTH
import kwitter.data.KweetRepository
import kwitter.data.UserRepository
import kwitter.data.model.Kweet
import kwitter.freemarker.homeFTL
import kwitter.freemarker.welcomeFTL
import kwitter.location.*

fun Route.index() {
    get<IndexLocation> {
        val session = call.sessions.get<KwitterSession>()
        if (session == null) {
            call.respond(guestIndex())
            return@get
        }

        val user = UserRepository.get(session.username)
        if (user == null) {
            call.respond(guestIndex())
            return@get
        }

        val kweets = KweetRepository.getAll()

        call.respond(loggedInIndex(user.displayName, kweets))
    }
}

private fun guestIndex() = welcomeFTL(
    signUpHref = SignUpLocation.path,
    loginHref = LoginLocation.path
)

private fun loggedInIndex(displayName: String, kweets: List<Kweet>) = homeFTL(
    displayName = displayName,
    logoutHref = LogoutLocation.path,
    kweetHref = KweetLocation.path,
    maxKweetLength = MAX_KWEET_LENGTH,
    kweets = kweets
)
