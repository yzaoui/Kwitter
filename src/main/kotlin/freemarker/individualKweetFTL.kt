package kwitter.freemarker

import io.ktor.freemarker.FreeMarkerContent
import kwitter.data.model.User

fun individualKweetFTLGuest(htmlKweet: HTMLKweet, loginURL: String) = FreeMarkerContent(
    template = "individual-kweet.ftl",
    model = mapOf(
        "htmlKweet" to htmlKweet,
        "loginURL" to loginURL
    )
)

fun individualKweetFTL(htmlKweet: HTMLKweet, loggedInUser: User, loggedInUserURL: String, logoutURL: String, generateAvatarURL: String) = FreeMarkerContent(
    template = "individual-kweet.ftl",
    model = mapOf(
        "htmlKweet" to htmlKweet,
        "loggedInUser" to loggedInUser,
        "loggedInUserURL" to loggedInUserURL,
        "logoutURL" to logoutURL,
        "generateAvatarURL" to generateAvatarURL
    )
)

fun individualKweetFTLNotFollowing(htmlKweet: HTMLKweet, followURL: String, loggedInUser: User, loggedInUserURL: String, logoutURL: String, generateAvatarURL: String) = FreeMarkerContent(
    template = "individual-kweet.ftl",
    model = mapOf(
        "htmlKweet" to htmlKweet,
        "followURL" to followURL,
        "loggedInUser" to loggedInUser,
        "loggedInUserURL" to loggedInUserURL,
        "logoutURL" to logoutURL,
        "generateAvatarURL" to generateAvatarURL
    )
)

fun individualKweetFTLFollowing(htmlKweet: HTMLKweet, unfollowURL: String, loggedInUser: User, loggedInUserURL: String, logoutURL: String, generateAvatarURL: String) = FreeMarkerContent(
    template = "individual-kweet.ftl",
    model = mapOf(
        "htmlKweet" to htmlKweet,
        "unfollowURL" to unfollowURL,
        "loggedInUser" to loggedInUser,
        "loggedInUserURL" to loggedInUserURL,
        "logoutURL" to logoutURL,
        "generateAvatarURL" to generateAvatarURL
    )
)

