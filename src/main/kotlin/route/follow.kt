package com.bitwiserain.kwitter.route

import com.bitwiserain.kwitter.KwitterSession
import com.bitwiserain.kwitter.data.UserRepository
import com.bitwiserain.kwitter.domain.usecase.FollowUser
import com.bitwiserain.kwitter.href
import io.ktor.application.call
import io.ktor.locations.Location
import io.ktor.locations.post
import io.ktor.response.respondRedirect
import io.ktor.routing.Route
import io.ktor.sessions.get
import io.ktor.sessions.sessions

@Location("/{username}/follow")
class FollowLocation(val username: String)

fun Route.follow(followUser: FollowUser) {
    post<FollowLocation> { followLocation ->
        val loggedInUser = call.sessions.get<KwitterSession>()?.let { UserRepository.get(it.userId) }
        if (loggedInUser == null) {
            call.respondRedirect(href(LoginLocation()))
            return@post
        }

        followUser.follow(loggedInUser.username, followLocation.username)
        call.respondRedirect(href(ProfileLocation(followLocation.username)))
    }
}
