package freemarker

import io.ktor.freemarker.FreeMarkerContent

fun welcomeFTL(signUpHref: String, loginHref: String) = FreeMarkerContent(
    template = "welcome.ftl",
    model = mapOf(
        "signUpHref" to signUpHref,
        "loginHref" to loginHref
    )
)
