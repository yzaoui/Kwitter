package kwitter

import freemarker.cache.ClassTemplateLoader
import io.ktor.application.Application
import io.ktor.application.ApplicationCall
import io.ktor.application.application
import io.ktor.application.install
import io.ktor.content.files
import io.ktor.content.resources
import io.ktor.content.static
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.freemarker.FreeMarker
import io.ktor.locations.Locations
import io.ktor.locations.locations
import io.ktor.pipeline.PipelineContext
import io.ktor.routing.Route
import io.ktor.routing.application
import io.ktor.routing.routing
import io.ktor.sessions.Sessions
import io.ktor.sessions.cookie
import kwitter.route.*

data class KwitterSession(val username: String)

fun Application.main() {
    install(DefaultHeaders)
    install(CallLogging)
    install(Locations)
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "")
    }
    install(Sessions) {
        cookie<KwitterSession>("kwitter_session") {
            cookie.path = "/"
        }
    }
    routing {
        index()
        login()
        logout()
        signUp()
        newKweet()
        profile()
        individualKweet()
        follow()
        unfollow()
        generateAvatar()
        static("assets") {
            static("css") {
                resources("css")
            }
            static("js") {
                resources("js")
            }
            static("images") {
                resources("images")
                static("user") {
                    files(environment.config.property("kwitter.avatar.dir").getString())
                }
            }
        }
    }
}

fun Route.href(location: Any) = application.locations.href(location)
fun PipelineContext<Unit, ApplicationCall>.href(location: Any) = application.locations.href(location)
