package freemarker

import io.ktor.freemarker.FreeMarkerContent
import kwitter.data.model.User

fun generateAvatarFTL(generateAvatarURL: String, loggedInUser: User, loggedInUserURL: String, logoutURL: String) = FreeMarkerContent(
    template = "generate-avatar.ftl",
    model = mapOf(
        "generateAvatarURL" to generateAvatarURL,
        "loggedInUser" to loggedInUser,
        "loggedInUserURL" to loggedInUserURL,
        "logoutURL" to logoutURL,
        "generateAvatarURL" to generateAvatarURL
    )
)
