package kwitter.location

import io.ktor.locations.Location

@Location("/{username}")
class ProfileLocation(val username: String)
