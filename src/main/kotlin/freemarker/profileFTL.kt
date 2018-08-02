package kwitter.freemarker

import io.ktor.freemarker.FreeMarkerContent
import kwitter.data.model.User
import kwitter.location.LoginLocation
import kwitter.location.LogoutLocation

fun profileFTLGuest(user: User, htmlKweets: List<HTMLKweet>) = FreeMarkerContent(
    template = "profile.ftl",
    model = mapOf(
        "user" to user,
        "htmlKweets" to htmlKweets,
        "loginHref" to LoginLocation.PATH
    )
)

fun profileFTL(user: User, htmlKweets: List<HTMLKweet>, loggedInUser: User, loggedInUserURL: String) = FreeMarkerContent(
    template = "profile.ftl",
    model = mapOf(
        "user" to user,
        "htmlKweets" to htmlKweets,
        "logoutHref" to LogoutLocation.PATH,
        "loggedInUser" to loggedInUser,
        "loggedInUserURL" to loggedInUserURL
    )
)
