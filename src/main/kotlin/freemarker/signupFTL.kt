package kwitter.freemarker

import io.ktor.freemarker.FreeMarkerContent

private fun signupFTLBuilder(model: Map<String, Any>) = FreeMarkerContent(
    template = "signup.ftl",
    model = model.plus(mapOf(
        "MIN_LENGTH_USERNAME" to 4, //TODO: Constants
        "MAX_LENGTH_USERNAME" to 15 //TODO: Constants
    ))
)

fun signupFTL(signUpHref: String) = signupFTLBuilder(mapOf(
    "signUpHref" to signUpHref
))

fun signupFTL(signUpHref: String, errorMessage: String) = signupFTLBuilder(mapOf(
    "signUpHref" to signUpHref,
    "errorMessage" to errorMessage
))
