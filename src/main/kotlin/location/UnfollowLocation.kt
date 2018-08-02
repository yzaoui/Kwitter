package kwitter.location

import io.ktor.locations.Location

@Location(UnfollowLocation.PATH)
class UnfollowLocation(val username: String) {
    companion object {
        const val PATH = "/{username}/unfollow"

        fun createPath(username: String) = "/$username/unfollow"
    }
}
