package kwitter.location

import io.ktor.locations.Location

@Location("/{username}/follow")
class FollowLocation(val username: String)
