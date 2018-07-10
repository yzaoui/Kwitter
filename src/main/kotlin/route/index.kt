package kwitter.route

import io.ktor.application.call
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import kwitter.KwitterSession
import kwitter.MAX_KWEET_LENGTH
import kwitter.USERNAME_REGEX
import kwitter.data.KweetRepository
import kwitter.data.UserRepository
import kwitter.data.model.Kweet
import kwitter.data.model.User
import kwitter.freemarker.homeFTL
import kwitter.freemarker.welcomeFTL
import kwitter.location.*

fun Route.index() {
    get<IndexLocation> {
        val user = call.sessions.get<KwitterSession>()?.username?.let { UserRepository.get(it) }
        if (user == null) {
            call.respond(guestIndex())
            return@get
        }

        val kweets = KweetRepository.getAll()

        call.respond(loggedInIndex(user, kweets, ProfileLocation.createPath(user.username)))
    }
}

private fun guestIndex() = welcomeFTL(
    signUpHref = SignUpLocation.PATH,
    loginHref = LoginLocation.PATH
)

private fun loggedInIndex(loggedInUser: User, kweets: List<Kweet>, profileURL: String) = homeFTL(
    loggedInUser = loggedInUser,
    logoutHref = LogoutLocation.PATH,
    kweetHref = KweetLocation.path,
    maxKweetLength = MAX_KWEET_LENGTH,
    kweets = kweets.map { wrapMentionsWithHtmlLinks(it) },
    profileURL = profileURL
)

private fun wrapMentionsWithHtmlLinks(kweet: Kweet): Kweet {
    return kweet.copy(text = "@$USERNAME_REGEX".toRegex().replace(kweet.text) {
        val username = it.value.removePrefix("@")
        if (UserRepository.get(username) != null) {
            "<a href=\"${ProfileLocation.createPath(username)}\">${it.value}</a>"
        } else {
            it.value
        }
    })
}
