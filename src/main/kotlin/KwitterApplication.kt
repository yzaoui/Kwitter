package kwitter

import freemarker.cache.ClassTemplateLoader
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.content.resources
import io.ktor.content.static
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.freemarker.FreeMarker
import io.ktor.locations.Location
import io.ktor.locations.Locations
import io.ktor.routing.routing
import io.ktor.sessions.Sessions
import io.ktor.sessions.cookie

@Location(Index.path)
class Index {
    companion object {
        const val path = "/"
    }
}

@Location(Login.path)
class Login {
    companion object {
        const val path = "/login"
    }
}

@Location(Logout.path)
class Logout {
    companion object {
        const val path = "/logout"
    }
}

@Location(SignUp.path)
class SignUp {
    companion object {
        const val path = "/signup"
    }
}

@Location(KweetLocation.path)
class KweetLocation {
    companion object {
        const val path = "/kweet"
    }
}

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
        static("assets") {
            static("css") {
                resources("css")
            }
        }
    }
}
