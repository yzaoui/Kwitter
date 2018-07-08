package kwitter.location

import io.ktor.locations.Location

@Location(KweetLocation.path)
class KweetLocation {
    companion object {
        const val path = "/kweet"
    }
}
