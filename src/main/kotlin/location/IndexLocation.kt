package kwitter.location

import io.ktor.locations.Location

@Location(IndexLocation.path)
class IndexLocation {
    companion object {
        const val path = "/"
    }
}
