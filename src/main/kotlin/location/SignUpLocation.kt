package kwitter.location

import io.ktor.locations.Location

@Location(SignUpLocation.path)
class SignUpLocation {
    companion object {
        const val path = "/signup"
    }
}
