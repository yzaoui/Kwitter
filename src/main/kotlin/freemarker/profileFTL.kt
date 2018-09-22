package com.bitwiserain.kwitter.freemarker

import com.bitwiserain.kwitter.domain.model.User
import io.ktor.freemarker.FreeMarkerContent

fun profileFTLGuest(user: User, htmlKweets: List<HTMLKweet>, loginURL: String) = FreeMarkerContent(
    template = "profile.ftl",
    model = mapOf(
        "user" to user,
        "htmlKweets" to htmlKweets,
        "loginURL" to loginURL
    )
)

fun profileFTL(user: User, htmlKweets: List<HTMLKweet>, logoutURL: String, loggedInUser: User, loggedInUserURL: String, generateAvatarURL: String) = FreeMarkerContent(
    template = "profile.ftl",
    model = mapOf(
        "user" to user,
        "htmlKweets" to htmlKweets,
        "logoutURL" to logoutURL,
        "loggedInUser" to loggedInUser,
        "loggedInUserURL" to loggedInUserURL,
        "generateAvatarURL" to generateAvatarURL
    )
)
