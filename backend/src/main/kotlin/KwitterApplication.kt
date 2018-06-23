package kwitter.backend

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.locations.Location
import io.ktor.locations.Locations
import io.ktor.routing.routing

@Location("/")
class Index

fun Application.main() {
    install(DefaultHeaders)
    install(CallLogging)
    install(Locations)
    routing {
        index()
    }
}
