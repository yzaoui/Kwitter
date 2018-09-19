package com.bitwiserain.kwitter.route

import com.bitwiserain.kwitter.KwitterSession
import com.bitwiserain.kwitter.href
import io.ktor.application.call
import io.ktor.locations.Location
import io.ktor.locations.post
import io.ktor.response.respondRedirect
import io.ktor.routing.Route
import io.ktor.sessions.clear
import io.ktor.sessions.sessions

@Location("/logout")
class LogoutLocation

fun Route.logout() {
    post<LogoutLocation> {
        call.sessions.clear<KwitterSession>()
        call.respondRedirect(href(IndexLocation()))
    }
}
