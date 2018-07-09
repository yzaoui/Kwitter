package kwitter.freemarker

import io.ktor.freemarker.FreeMarkerContent
import kwitter.USERNAME_REGEX
import kwitter.location.SignUpLocation

fun signupFTL() = signupFTLDefault()

fun signupFTL(errorMessage: String) = signupFTLDefault(mapOf(
    "errorMessage" to errorMessage
))

private fun signupFTLDefault(model: Map<String, Any> = emptyMap()) = FreeMarkerContent(
    template = "signup.ftl",
    model = model.plus(mapOf(
        "SIGN_UP_HREF" to SignUpLocation.PATH,
        "USERNAME_REGEX" to USERNAME_REGEX
    ))
)
