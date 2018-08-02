package kwitter.location

import io.ktor.locations.Location

@Location("/{username}/unfollow")
class UnfollowLocation(val username: String)
