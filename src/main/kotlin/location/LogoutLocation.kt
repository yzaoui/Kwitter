package kwitter.location

import io.ktor.locations.Location

@Location(LogoutLocation.path)
class LogoutLocation {
    companion object {
        const val path = "/logout"
    }
}
