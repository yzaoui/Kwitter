package kwitter.route

import io.ktor.application.call
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.routing.Route
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import kwitter.KwitterSession
import kwitter.MAX_KWEET_LENGTH
import kwitter.USERNAME_REGEX
import kwitter.data.UserRepository
import kwitter.data.model.Kweet
import kwitter.domain.usecase.ListHomeKweets
import kwitter.freemarker.HTMLKweet
import kwitter.freemarker.homeFTL
import kwitter.freemarker.welcomeFTL
import kwitter.href

@Location("/")
class IndexLocation

fun Route.index() {
    get<IndexLocation> {
        val loggedInUser = call.sessions.get<KwitterSession>()?.username?.let { UserRepository.get(it) }
        if (loggedInUser == null) {
            call.respond(welcomeFTL(
                signUpHref = href(SignUpLocation()),
                loginHref = href(LoginLocation())
            ))
            return@get
        }

        val kweetsFollowing = ListHomeKweets.getKweetsInReverseChronologicalOrder(loggedInUser.username)
        if (kweetsFollowing == null) {
            call.respondRedirect(href(IndexLocation()))
            return@get
        }

        val htmlKweets = kweetsFollowing
            .map { it.toHTMLKweet(UserRepository, { username, kweetId -> href(IndividualKweetLocation(username, kweetId)) }, { username -> href(ProfileLocation(username)) }) }

        call.respond(homeFTL(
            loggedInUser = loggedInUser,
            loggedInUserURL = href(ProfileLocation(loggedInUser.username)),
            logoutURL = href(LogoutLocation()),
            newKweetHref = href(NewKweetLocation()),
            maxKweetLength = MAX_KWEET_LENGTH,
            htmlKweets = htmlKweets
        ))
    }
}

fun Kweet.toHTMLKweet(
    userRepo: UserRepository,
    kweetURLGenerator: (username: String, kweetId: String) -> String,
    profileURLGenerator: (username: String) -> String
): HTMLKweet {
    val user = userRepo.get(username)!!
    return HTMLKweet(
        id = id,
        text = text,
        html = "@$USERNAME_REGEX".toRegex().replace(text) {
            val username = it.value.removePrefix("@")
            if (UserRepository.get(username) != null) {
                "<a href=\"${profileURLGenerator(username)}\">${it.value}</a>"
            } else {
                it.value
            }
        },
        dateText = date.toString(),
        kweetURL = kweetURLGenerator(username, id),
        authorDisplayName = user.displayName,
        authorUsername = user.username,
        authorProfilePictureURL = user.profilePictureURL,
        authorProfileURL = profileURLGenerator(user.username)
    )
}
