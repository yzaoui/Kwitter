package kwitter

import freemarker.cache.ClassTemplateLoader
import io.ktor.application.Application
import io.ktor.application.ApplicationCall
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.authentication
import io.ktor.content.files
import io.ktor.content.resources
import io.ktor.content.static
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.freemarker.FreeMarker
import io.ktor.locations.Location
import io.ktor.locations.Locations
import io.ktor.locations.locations
import io.ktor.request.host
import io.ktor.request.port
import io.ktor.response.respondRedirect
import io.ktor.routing.routing

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

@Location(SignUp.path)
class SignUp {
    companion object {
        const val path = "/signup"
    }
}

fun Application.main() {
    install(DefaultHeaders)
    install(CallLogging)
    install(Locations)
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "")
    }
    routing {
        index()
        login()
        signUp()
        static("static") {
            resources("css")
        }
    }
}
