package kwitter

import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.sessions.clear
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import kwitter.freemarker.homeFTL
import kwitter.freemarker.welcomeFTL

fun Route.index() {
    get<Index> {
        val session = call.sessions.get<KwitterSession>()

        if (session == null) {
            call.respond(guestIndex())
        } else {
            val user = UserRepository.get(session.username)

            call.respond(if (user != null) loggedInIndex(displayName = user.displayName) else guestIndex())
        }
    }
}

private fun guestIndex() = welcomeFTL(
    signUpHref = SignUp.path,
    loginHref = Login.path
)

private fun loggedInIndex(displayName: String) = homeFTL(
    displayName = displayName,
    logoutHref = Logout.path
)
