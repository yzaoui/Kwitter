package com.bitwiserain.kwitter.route

import com.bitwiserain.kwitter.KwitterSession
import com.bitwiserain.kwitter.data.UserRepository
import com.bitwiserain.kwitter.freemarker.generateAvatarFTL
import com.bitwiserain.kwitter.href
import com.bitwiserain.kwitter.stringToBufferedImage
import io.ktor.application.application
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.routing.Route
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO

@Location("/generate-avatar")
class GenerateAvatarLocation()

fun Route.generateAvatar() {
    get<GenerateAvatarLocation> {
        val loggedInUser = call.sessions.get<KwitterSession>()?.let { UserRepository.get(it.userId) }
        if (loggedInUser == null) {
            call.respondRedirect(href(LoginLocation()))
            return@get
        }

        call.respond(generateAvatarFTL(href(GenerateAvatarLocation()), loggedInUser, href(ProfileLocation(loggedInUser.username)), href(LogoutLocation())))
    }
    post<GenerateAvatarLocation> {
        val loggedInUser = call.sessions.get<KwitterSession>()?.let { UserRepository.get(it.userId) }
        if (loggedInUser == null) {
            call.respondRedirect(href(LoginLocation()))
            return@post
        }

        val image = stringToBufferedImage(loggedInUser.username)
        val filename = loggedInUser.username + ".bmp"
        try {
            val file = File(application.environment.config.property("kwitter.avatar.dir").getString() + filename)
            file.parentFile.mkdirs()
            ImageIO.write(image, "BMP", file)
        } catch (e: IOException) {
            call.respond(HttpStatusCode.InternalServerError)
            return@post
        }

        UserRepository.changeProfilePictureURL(loggedInUser.id, "/assets/images/user/$filename")

        call.respondRedirect(href(ProfileLocation(loggedInUser.username)))
    }
}
