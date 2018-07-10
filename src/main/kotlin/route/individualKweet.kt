package kwitter.route

import io.ktor.locations.get
import io.ktor.routing.Route
import kwitter.location.IndividualKweetLocation

fun Route.individualKweet() {
    get<IndividualKweetLocation> {
        
    }
}
