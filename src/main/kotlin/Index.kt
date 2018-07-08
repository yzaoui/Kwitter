package kwitter

import io.ktor.application.call
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import kwitter.data.KweetRepository
import kwitter.data.UserRepository
import kwitter.data.model.Kweet
import kwitter.freemarker.homeFTL
import kwitter.freemarker.welcomeFTL

fun Route.index() {
    get<Index> {
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
    signUpHref = SignUp.path,
    loginHref = Login.path
)

private fun loggedInIndex(displayName: String, kweets: List<Kweet>) = homeFTL(
    displayName = displayName,
    logoutHref = Logout.path,
    kweetHref = KweetLocation.path,
    maxKweetLength = MAX_KWEET_LENGTH,
    kweets = kweets
)
