package com.bitwiserain.kwitter.freemarker

import io.ktor.freemarker.FreeMarkerContent
import com.bitwiserain.kwitter.USERNAME_REGEX

fun signupFTL(signUpURL: String) = FreeMarkerContent(
    template = "signup.ftl",
    model = mapOf(
        "signUpURL" to signUpURL,
        "USERNAME_REGEX" to USERNAME_REGEX
    )
)

fun signupFTLError(signUpURL: String, errorMessage: String) = FreeMarkerContent(
    template = "signup.ftl",
    model = mapOf(
        "signUpURL" to signUpURL,
        "USERNAME_REGEX" to USERNAME_REGEX,
        "errorMessage" to errorMessage
    )
)
