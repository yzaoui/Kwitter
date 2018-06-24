package kwitter.backend

import freemarker.cache.ClassTemplateLoader
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.freemarker.FreeMarker
import io.ktor.locations.Location
import io.ktor.locations.Locations
import io.ktor.routing.routing

@Location("/")
class Index

@Location("/login")
class Login

@Location("/signup")
class SignUp

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
    }
}
