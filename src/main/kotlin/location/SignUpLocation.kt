package kwitter.location

import io.ktor.locations.Location

@Location(SignUpLocation.PATH)
class SignUpLocation {
    companion object {
        const val PATH = "/signup"
    }
}
