package com.bitwiserain.kwitter.domain.usecase

interface CheckFollow {
    fun follows(usernameFollower: String, usernameTarget: String): Boolean
}
