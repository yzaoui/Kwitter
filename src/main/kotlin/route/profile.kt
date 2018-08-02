package kwitter.route

import io.ktor.application.call
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.routing.Route
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import kwitter.KwitterSession
import kwitter.data.UserRepository
import kwitter.domain.usecase.ListUserKweets
import kwitter.freemarker.profileFTL
import kwitter.freemarker.profileFTLGuest
import kwitter.href
import kwitter.location.*

fun Route.profile() {
    get<ProfileLocation> { profileLocation ->
        val user = UserRepository.get(profileLocation.username)

        if (user == null) {
            call.respondRedirect(href(IndexLocation()))
            return@get
        }

        val loggedInUser = call.sessions.get<KwitterSession>()?.username?.let { UserRepository.get(it) }
        val htmlKweets = ListUserKweets.getKweetsInReverseChronologicalOrder(user.username)
            .map {
                it.toHTMLKweet(UserRepository, { username, kweetId ->  href(IndividualKweetLocation(username, kweetId)) }, { username -> href(ProfileLocation(username)) })
            }

        if (loggedInUser != null) {
            call.respond(profileFTL(user, htmlKweets, href(LogoutLocation()), loggedInUser, href(ProfileLocation(loggedInUser.username))))
        } else {
            call.respond(profileFTLGuest(user, htmlKweets, href(LoginLocation())))
        }
    }
}
