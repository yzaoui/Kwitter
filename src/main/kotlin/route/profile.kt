package com.bitwiserain.kwitter.route

import com.bitwiserain.kwitter.KwitterSession
import com.bitwiserain.kwitter.db.repository.UserRepository
import com.bitwiserain.kwitter.domain.usecase.ListUserKweets
import com.bitwiserain.kwitter.freemarker.profileFTL
import com.bitwiserain.kwitter.freemarker.profileFTLGuest
import com.bitwiserain.kwitter.href
import io.ktor.application.call
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.routing.Route
import io.ktor.sessions.get
import io.ktor.sessions.sessions

@Location("/{username}")
class ProfileLocation(val username: String)

fun Route.profile(listUserKweets: ListUserKweets) {
    get<ProfileLocation> { profileLocation ->
        val user = UserRepository.getByUsername(profileLocation.username)
        if (user == null) {
            call.respondRedirect(href(IndexLocation()))
            return@get
        }

        val loggedInUser = call.sessions.get<KwitterSession>()?.let { UserRepository.get(it.userId) }

        val htmlKweets = listUserKweets.getKweetsInReverseChronologicalOrder(user.username)
            .map {
                it.toHTMLKweet(UserRepository, { username, kweetId ->  href(IndividualKweetLocation(username, kweetId)) }, { username -> href(ProfileLocation(username)) })
            }

        if (loggedInUser != null) {
            call.respond(profileFTL(user, htmlKweets, href(LogoutLocation()), loggedInUser, href(ProfileLocation(loggedInUser.username)), generateAvatarURL = href(GenerateAvatarLocation())))
        } else {
            call.respond(profileFTLGuest(user, htmlKweets, href(LoginLocation())))
        }
    }
}
