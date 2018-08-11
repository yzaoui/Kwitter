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
import kwitter.domain.usecase.FollowUser
import kwitter.href

@Location("/{username}/follow")
class FollowLocation(val username: String)

fun Route.follow(followUser: FollowUser) {
    post<FollowLocation> { followLocation ->
        val loggedInUser = call.sessions.get<KwitterSession>()?.username?.let { UserRepository.get(it) }
        if (loggedInUser == null) {
            call.respondRedirect(href(LoginLocation()))
            return@post
        }

        followUser.follow(loggedInUser.username, followLocation.username)
        call.respondRedirect(href(ProfileLocation(followLocation.username)))
    }
}
