package kwitter.location

import io.ktor.locations.Location

@Location(ProfileLocation.path)
class ProfileLocation(val username: String) {
    companion object {
        const val path = "/{username}"
    }
}
