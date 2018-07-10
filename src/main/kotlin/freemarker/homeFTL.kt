package kwitter.freemarker

import io.ktor.freemarker.FreeMarkerContent
import kwitter.data.model.User

fun homeFTL(loggedInUser: User, logoutHref: String, newKweetHref: String, maxKweetLength: Int, htmlKweets: List<HTMLKweet>, profileURL: String) = FreeMarkerContent(
    template = "home.ftl",
    model = mapOf(
        "loggedInUser" to loggedInUser,
        "logoutHref" to logoutHref,
        "newKweetHref" to newKweetHref,
        "maxKweetLength" to maxKweetLength,
        "htmlKweets" to htmlKweets,
        "profileURL" to profileURL
    )
)

data class HTMLKweet (
    val id: String,
    val html: String,
    val dateText: String,
    val kweetURL: String,
    val authorDisplayName: String,
    val authorUsername: String,
    val authorProfilePictureURL: String,
    val authorProfileURL: String
)
