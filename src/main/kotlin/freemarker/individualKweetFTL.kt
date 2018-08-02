package kwitter.freemarker

import io.ktor.freemarker.FreeMarkerContent
import kwitter.data.model.User
import kwitter.location.LoginLocation
import kwitter.location.LogoutLocation

fun individualKweetFTLGuest(htmlKweet: HTMLKweet) = FreeMarkerContent(
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
        "loginHref" to LoginLocation.PATH,
        "loggedInUser" to loggedInUser,
        "loggedInUserURL" to loggedInUserURL,
        "logoutHref" to LogoutLocation.PATH
    )
)

fun individualKweetFTLNotFollowing(htmlKweet: HTMLKweet, followURL: String, loggedInUser: User, loggedInUserURL: String) = FreeMarkerContent(
    template = "individual-kweet.ftl",
    model = mapOf(
        "htmlKweet" to htmlKweet,
        "followURL" to followURL,
        "loggedInUser" to loggedInUser,
        "loggedInUserURL" to loggedInUserURL,
        "logoutHref" to LogoutLocation.PATH
    )
)

fun individualKweetFTLFollowing(htmlKweet: HTMLKweet, unfollowURL: String, loggedInUser: User, loggedInUserURL: String) = FreeMarkerContent(
    template = "individual-kweet.ftl",
    model = mapOf(
        "htmlKweet" to htmlKweet,
        "unfollowURL" to unfollowURL,
        "loggedInUser" to loggedInUser,
        "loggedInUserURL" to loggedInUserURL,
        "logoutHref" to LogoutLocation.PATH
    )
)

