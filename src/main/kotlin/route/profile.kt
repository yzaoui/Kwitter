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
import kwitter.location.IndexLocation
import kwitter.location.ProfileLocation

fun Route.profile() {
    get<ProfileLocation> { profileLocation ->
        val user = UserRepository.get(profileLocation.username)

        if (user == null) {
            call.respondRedirect(IndexLocation.PATH)
            return@get
        }

        val loggedInUser = call.sessions.get<KwitterSession>()?.username?.let { UserRepository.get(it) }
        val htmlKweets = ListUserKweets.getKweets(user.username).map { it.toHTMLKweet(UserRepository) }

        call.respond(if (loggedInUser != null) profileFTL(user, htmlKweets, loggedInUser, ProfileLocation.createPath(loggedInUser.username)) else profileFTLGuest(user, htmlKweets))
    }
}
