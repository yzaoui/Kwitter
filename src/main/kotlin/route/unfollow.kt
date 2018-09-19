package com.bitwiserain.kwitter.route

import com.bitwiserain.kwitter.KwitterSession
import com.bitwiserain.kwitter.data.UserRepository
import com.bitwiserain.kwitter.domain.usecase.UnfollowUser
import com.bitwiserain.kwitter.href
import io.ktor.application.call
import io.ktor.locations.Location
import io.ktor.locations.post
import io.ktor.response.respondRedirect
import io.ktor.routing.Route
import io.ktor.sessions.get
import io.ktor.sessions.sessions

@Location("/{username}/unfollow")
class UnfollowLocation(val username: String)

fun Route.unfollow(unfollowUser: UnfollowUser) {
    post<UnfollowLocation> { unfollowLocation ->
        val loggedInUser = call.sessions.get<KwitterSession>()?.let { UserRepository.get(it.userId) }
        if (loggedInUser == null) {
            call.respondRedirect(href(LoginLocation()))
            return@post
        }

        unfollowUser.unfollow(loggedInUser.username, unfollowLocation.username)
        call.respondRedirect(href(IndexLocation()))
    }
}
