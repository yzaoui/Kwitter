package com.bitwiserain.kwitter.freemarker

import com.bitwiserain.kwitter.data.model.User
import io.ktor.freemarker.FreeMarkerContent

fun generateAvatarFTL(generateAvatarURL: String, loggedInUser: User, loggedInUserURL: String, logoutURL: String) = FreeMarkerContent(
    template = "generate-avatar.ftl",
    model = mapOf(
        "generateAvatarURL" to generateAvatarURL,
        "loggedInUser" to loggedInUser,
        "loggedInUserURL" to loggedInUserURL,
        "logoutURL" to logoutURL,
        "generateAvatarURL" to generateAvatarURL
    )
)
