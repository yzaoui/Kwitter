package kwitter.freemarker

import io.ktor.freemarker.FreeMarkerContent
import kwitter.data.model.User
import java.time.Instant

fun homeFTL(loggedInUser: User, logoutHref: String, kweetHref: String, maxKweetLength: Int, htmlKweets: List<HTMLKweet>, profileURL: String) = FreeMarkerContent(
    template = "home.ftl",
    model = mapOf(
        "loggedInUser" to loggedInUser,
        "logoutHref" to logoutHref,
        "kweetHref" to kweetHref,
        "maxKweetLength" to maxKweetLength,
        "htmlKweets" to htmlKweets,
        "profileURL" to profileURL
    )
)

data class HTMLKweet (
    val id: Int,
    val html: String,
    val date: Instant,
    val authorDisplayName: String,
    val authorUsername: String,
    val authorProfilePictureURL: String
)
