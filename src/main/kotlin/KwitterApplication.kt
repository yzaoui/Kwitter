package kwitter

import freemarker.cache.ClassTemplateLoader
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.content.files
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
import kwitter.model.User

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

data class KwitterSession(val username: String)

object UserRepository {
    private val users: MutableMap<String, User> = mutableMapOf()

    fun create(user: User) {
        users[user.username] = user
    }

    fun get(username: String): User? {
        return users[username]
    }
}

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
        static("assets") {
            static("css") {
                resources("css")
            }
        }
    }
}
