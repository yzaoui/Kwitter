package kwitter.freemarker

import io.ktor.freemarker.FreeMarkerContent
import kwitter.data.model.Kweet

fun homeFTL(displayName: String, logoutHref: String, kweetHref: String, maxKweetLength: Int, kweets: List<Kweet>) = FreeMarkerContent(
    template = "home.ftl",
    model = mapOf(
        "displayName" to displayName,
        "logoutHref" to logoutHref,
        "kweetHref" to kweetHref,
        "maxKweetLength" to maxKweetLength,
        "kweets" to kweets
    )
)
