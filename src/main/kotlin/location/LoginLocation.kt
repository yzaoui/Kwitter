package kwitter.location

import io.ktor.locations.Location

@Location(LoginLocation.PATH)
class LoginLocation {
    companion object {
        const val PATH = "/login"
    }
}
