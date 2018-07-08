package kwitter.freemarker

import io.ktor.freemarker.FreeMarkerContent

fun homeFTL(displayName: String, logoutHref: String, kweetHref: String) = FreeMarkerContent(
    template = "home.ftl",
    model = mapOf(
        "displayName" to displayName,
        "logoutHref" to logoutHref,
        "kweetHref" to kweetHref
    )
)
