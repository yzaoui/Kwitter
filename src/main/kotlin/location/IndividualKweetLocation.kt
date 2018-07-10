package kwitter.location

import io.ktor.locations.Location

@Location(IndividualKweetLocation.PATH)
class IndividualKweetLocation(val username: String, val kweetId: String) {
    companion object {
        const val PATH = "/{username}/kweet/{kweetId}"

        fun createPath(username: String, kweetId: String) = "/$username/kweet/$kweetId"
    }
}
