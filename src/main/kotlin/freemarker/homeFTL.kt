package kwitter.freemarker

import io.ktor.freemarker.FreeMarkerContent
import kwitter.data.model.Kweet
import kwitter.data.model.User

fun homeFTL(loggedInUser: User, logoutHref: String, kweetHref: String, maxKweetLength: Int, kweets: List<Kweet>, profileURL: String) = FreeMarkerContent(
    template = "home.ftl",
    model = mapOf(
        "loggedInUser" to loggedInUser,
        "logoutHref" to logoutHref,
        "kweetHref" to kweetHref,
        "maxKweetLength" to maxKweetLength,
        "kweets" to kweets,
        "profileURL" to profileURL
    )
)
