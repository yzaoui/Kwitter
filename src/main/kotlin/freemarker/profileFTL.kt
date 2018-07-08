package kwitter.freemarker

import io.ktor.freemarker.FreeMarkerContent
import kwitter.data.model.User

fun profileFTL(user: User) = FreeMarkerContent(
    template = "profile.ftl",
    model = mapOf(
        "user" to user
    )
)
