package com.bitwiserain.kwitter.domain.usecase

interface UsernameAvailability {
    fun check(username: String): Boolean
}
