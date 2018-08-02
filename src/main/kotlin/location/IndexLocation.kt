package kwitter.location

import io.ktor.locations.Location

@Location(IndexLocation.PATH)
class IndexLocation {
    companion object {
        const val PATH = "/"
    }
}
