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
import kwitter.data.*
import kwitter.domain.usecase.*
import kwitter.route.*
import org.h2.Driver
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

data class KwitterSession(val userId: Int)

// Use cases
private val checkFollow = CheckFollowImpl()
private val followUser = FollowUserImpl()
private val unfollowUser = UnfollowUserImpl()
private val listHomeKweets = ListHomeKweetsImpl()
private val listUserKweets = ListUserKweetsImpl()
private val usernameAvailability = UsernameAvailabilityImpl(RESERVED_USERNAMES)

fun Application.main() {
    Database.connect("jdbc:h2:./testDB", Driver::class.qualifiedName!!)
    transaction {
        addLogger(Slf4jSqlDebugLogger)
        SchemaUtils.create(UserTable, KweetTable, FollowsTable)
    }
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
        index(listHomeKweets)
        login()
        logout()
        signUp(usernameAvailability)
        newKweet()
        profile(listUserKweets)
        individualKweet(checkFollow)
        follow(followUser)
        unfollow(unfollowUser)
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
