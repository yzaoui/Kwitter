package kwitter.location

import io.ktor.locations.Location

@Location("/{username}/kweet/{kweetId}")
class IndividualKweetLocation(val username: String, val kweetId: String)
