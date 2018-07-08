package kwitter.location

import io.ktor.locations.Location

@Location(LoginLocation.path)
class LoginLocation {
    companion object {
        const val path = "/login"
    }
}
