package kwitter.location

import io.ktor.locations.Location

@Location(FollowLocation.PATH)
class FollowLocation(val username: String) {
    companion object {
        const val PATH = "/{username}/follow"

        fun createPath(username: String) = "/$username/follow"
    }
}
