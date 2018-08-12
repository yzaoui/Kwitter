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
import kwitter.KWEET_MAX_LENGTH
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

fun Route.index(listHomeKweets: ListHomeKweets) {
    get<IndexLocation> {
        val loggedInUser = call.sessions.get<KwitterSession>()?.let { UserRepository.get(it.userId) }
        if (loggedInUser == null) {
            call.respond(welcomeFTL(
                signUpHref = href(SignUpLocation()),
                loginHref = href(LoginLocation())
            ))
            return@get
        }

        //TODO: Temp replace null with empty
        val kweetsFollowing = listHomeKweets.getHomeKweetsInReverseChronologicalOrder(loggedInUser.id) ?: listOf()
        if (kweetsFollowing == null) {
            //TODO: Respond with some error indicating can't load kweets
            call.respondRedirect(href(LoginLocation()))
            return@get
        }

        val htmlKweets = kweetsFollowing
            .map { it.toHTMLKweet(UserRepository, { username, kweetId -> href(IndividualKweetLocation(username, kweetId)) }, { username -> href(ProfileLocation(username)) }) }

        call.respond(homeFTL(
            loggedInUser = loggedInUser,
            loggedInUserURL = href(ProfileLocation(loggedInUser.username)),
            logoutURL = href(LogoutLocation()),
            newKweetHref = href(NewKweetLocation()),
            maxKweetLength = KWEET_MAX_LENGTH,
            htmlKweets = htmlKweets,
            generateAvatarURL = href(GenerateAvatarLocation())
        ))
    }
}

fun Kweet.toHTMLKweet(
    userRepo: UserRepository,
    kweetURLGenerator: (username: String, kweetId: String) -> String,
    profileURLGenerator: (username: String) -> String
): HTMLKweet {
    val author = userRepo.get(authorId)!!
    return HTMLKweet(
        id = id.toString(),
        text = text,
        html = "@$USERNAME_REGEX".toRegex().replace(text) {
            val username = it.value.removePrefix("@")
            if (UserRepository.getByUsername(username) != null) {
                "<a href=\"${profileURLGenerator(username)}\">${it.value}</a>"
            } else {
                it.value
            }
        },
        dateText = date.toString(),
        kweetURL = kweetURLGenerator(author.username, id.toString()),
        authorDisplayName = author.displayName,
        authorUsername = author.username,
        authorProfilePictureURL = author.profilePictureURL,
        authorProfileURL = profileURLGenerator(author.username)
    )
}
