package com.bitwiserain.kwitter.domain.usecase

interface UnfollowUser {
    fun unfollow(usernameFollower: String, usernameTarget: String)
}
