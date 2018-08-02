package kwitter.route

import io.ktor.application.call
import io.ktor.locations.post
import io.ktor.response.respondRedirect
import io.ktor.routing.Route
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import kwitter.KwitterSession
import kwitter.data.UserRepository
import kwitter.domain.usecase.FollowUser
import kwitter.location.FollowLocation
import kwitter.location.LoginLocation
import kwitter.location.ProfileLocation

fun Route.follow() {
    post<FollowLocation> { followLocation ->
        val loggedInUser = call.sessions.get<KwitterSession>()?.username?.let { UserRepository.get(it) }
        if (loggedInUser == null) {
            call.respondRedirect(LoginLocation.PATH)
            return@post
        }

        FollowUser.follow(loggedInUser.username, followLocation.username)
        call.respondRedirect(ProfileLocation.createPath(followLocation.username))
    }
}
