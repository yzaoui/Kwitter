package com.bitwiserain.kwitter.domain.usecase

import com.bitwiserain.kwitter.domain.model.Kweet

interface ListHomeKweets {
    fun getHomeKweetsInReverseChronologicalOrder(userId: Int): Iterable<Kweet>?
}
