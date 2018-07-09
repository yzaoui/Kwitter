package kwitter.location

import io.ktor.locations.Location

@Location(LogoutLocation.PATH)
class LogoutLocation {
    companion object {
        const val PATH = "/logout"
    }
}
