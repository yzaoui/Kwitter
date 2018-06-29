package freemarker

import io.ktor.freemarker.FreeMarkerContent

fun signupFTL(signUpHref: String) = FreeMarkerContent(
    template = "signup.ftl",
    model = mapOf(
        "signUpHref" to signUpHref
    )
)

fun signupFTL(signUpHref: String, errorMessage: String) = FreeMarkerContent(
    template = "signup.ftl",
    model = mapOf(
        "signUpHref" to signUpHref,
        "errorMessage" to errorMessage
    )
)
