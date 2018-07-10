package kwitter.freemarker

import io.ktor.freemarker.FreeMarkerContent
import kwitter.data.model.User
import kwitter.location.LoginLocation
import kwitter.location.LogoutLocation

fun profileFTL(user: User) = FreeMarkerContent(
    template = "profile.ftl",
    model = mapOf(
        "user" to user,
        "loginHref" to LoginLocation.PATH
    )
)

fun profileFTL(user: User, loggedInUser: User, loggedInUserURL: String) = FreeMarkerContent(
    template = "profile.ftl",
    model = mapOf(
        "user" to user,
        "logoutHref" to LogoutLocation.PATH,
        "loggedInUser" to loggedInUser,
        "loggedInUserURL" to loggedInUserURL
    )
)
