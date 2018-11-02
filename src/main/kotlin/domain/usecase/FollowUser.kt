package com.bitwiserain.kwitter.domain.usecase

interface FollowUser {
    fun follow(usernameFollower: String, usernameTarget: String)
}
