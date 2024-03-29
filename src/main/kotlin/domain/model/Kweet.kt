package com.bitwiserain.kwitter.domain.model

import java.time.Instant

data class Kweet(
    val id: Int,
    val authorId: Int,
    val text: String,
    val date: Instant
)
