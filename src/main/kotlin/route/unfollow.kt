package kwitter.route

import io.ktor.application.call
import io.ktor.locations.Location
import io.ktor.locations.post
import io.ktor.response.respondRedirect
import io.ktor.routing.Route
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import kwitter.KwitterSession
import kwitter.data.UserRepository
import kwitter.domain.usecase.UnfollowUser
import kwitter.href

@Location("/{username}/unfollow")
class UnfollowLocation(val username: String)

fun Route.unfollow() {
    post<UnfollowLocation> { unfollowLocation ->
        val loggedInUser = call.sessions.get<KwitterSession>()?.username?.let { UserRepository.get(it) }
        if (loggedInUser == null) {
            call.respondRedirect(href(LoginLocation()))
            return@post
        }

        UnfollowUser.unfollow(loggedInUser.username, unfollowLocation.username)
        call.respondRedirect(href(IndexLocation()))
    }
}
