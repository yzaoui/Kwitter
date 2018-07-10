package kwitter

import freemarker.cache.ClassTemplateLoader
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.content.resources
import io.ktor.content.static
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.freemarker.FreeMarker
import io.ktor.locations.Locations
import io.ktor.routing.routing
import io.ktor.sessions.Sessions
import io.ktor.sessions.cookie
import kwitter.route.*

data class KwitterSession(val username: String)

const val MAX_KWEET_LENGTH = 280

fun Application.main() {
    install(DefaultHeaders)
    install(CallLogging)
    install(Locations)
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "")
    }
    install(Sessions) {
        cookie<KwitterSession>("kwitter_session")
    }
    routing {
        index()
        login()
        logout()
        signUp()
        kweet()
        profile()
        individualKweet()
        static("assets") {
            static("css") {
                resources("css")
            }
            static("images") {
                resources("images")
            }
        }
    }
}
