package kwitter.route

import io.ktor.application.call
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.routing.Route
import kwitter.data.UserRepository
import kwitter.freemarker.profileFTL
import kwitter.location.IndexLocation
import kwitter.location.ProfileLocation

fun Route.profile() {
    get<ProfileLocation> { profileLocation ->
        val user = UserRepository.get(profileLocation.username)

        if (user == null) {
            call.respondRedirect(IndexLocation.path)
            return@get
        }

        call.respond(profileFTL(user))
    }
}
