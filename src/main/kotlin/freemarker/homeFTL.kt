package kwitter.freemarker

import io.ktor.freemarker.FreeMarkerContent
import kwitter.data.model.User

fun homeFTL(loggedInUser: User, loggedInUserURL: String, logoutURL: String, newKweetHref: String, maxKweetLength: Int, htmlKweets: List<HTMLKweet>, generateAvatarURL: String) = FreeMarkerContent(
    template = "home.ftl",
    model = mapOf(
        "loggedInUser" to loggedInUser,
        "loggedInUserURL" to loggedInUserURL,
        "logoutURL" to logoutURL,
        "newKweetHref" to newKweetHref,
        "maxKweetLength" to maxKweetLength,
        "htmlKweets" to htmlKweets,
        "generateAvatarURL" to generateAvatarURL
    )
)

data class HTMLKweet (
    val id: String,
    val text: String,
    val html: String,
    val dateText: String,
    val kweetURL: String,
    val authorDisplayName: String,
    val authorUsername: String,
    val authorProfilePictureURL: String,
    val authorProfileURL: String
)
