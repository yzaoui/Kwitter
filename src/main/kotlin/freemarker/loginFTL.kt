package kwitter.freemarker

import io.ktor.freemarker.FreeMarkerContent

fun loginFTL(loginHref: String) = FreeMarkerContent(
    template = "login.ftl",
    model = mapOf(
        "loginHref" to loginHref
    )
)

fun loginFTL(loginHref: String, errorMessage: String) = FreeMarkerContent(
    template = "login.ftl",
    model = mapOf(
        "loginHref" to loginHref,
        "errorMessage" to errorMessage
    )
)
