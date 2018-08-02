package kwitter.freemarker

import io.ktor.freemarker.FreeMarkerContent
import kwitter.data.model.User

fun profileFTLGuest(user: User, htmlKweets: List<HTMLKweet>, loginURL: String) = FreeMarkerContent(
    template = "profile.ftl",
    model = mapOf(
        "user" to user,
        "htmlKweets" to htmlKweets,
        "loginURL" to loginURL
    )
)

fun profileFTL(user: User, htmlKweets: List<HTMLKweet>, logoutURL: String, loggedInUser: User, loggedInUserURL: String) = FreeMarkerContent(
    template = "profile.ftl",
    model = mapOf(
        "user" to user,
        "htmlKweets" to htmlKweets,
        "logoutURL" to logoutURL,
        "loggedInUser" to loggedInUser,
        "loggedInUserURL" to loggedInUserURL
    )
)
