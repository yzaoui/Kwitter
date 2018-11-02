package com.bitwiserain.kwitter.domain.usecase

import com.bitwiserain.kwitter.domain.model.Kweet

interface ListUserKweets {
    fun getKweetsInReverseChronologicalOrder(username: String): Iterable<Kweet>
}
