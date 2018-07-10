package kwitter.freemarker

import io.ktor.freemarker.FreeMarkerContent
import kwitter.data.model.User
import kwitter.location.LoginLocation
import kwitter.location.LogoutLocation

fun individualKweetFTL(htmlKweet: HTMLKweet) = FreeMarkerContent(
    template = "individual-kweet.ftl",
    model = mapOf(
        "htmlKweet" to htmlKweet,
        "loginHref" to LoginLocation.PATH
    )
)

fun individualKweetFTL(htmlKweet: HTMLKweet, loggedInUser: User, loggedInUserURL: String) = FreeMarkerContent(
    template = "individual-kweet.ftl",
    model = mapOf(
        "htmlKweet" to htmlKweet,
        "loggedInUser" to loggedInUser,
        "loggedInUserURL" to loggedInUserURL,
        "logoutHref" to LogoutLocation.PATH
    )
)

