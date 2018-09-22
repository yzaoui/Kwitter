package com.bitwiserain.kwitter.domain.usecase

import com.bitwiserain.kwitter.data.FollowsTable
import com.bitwiserain.kwitter.data.KweetTable
import com.bitwiserain.kwitter.data.UserTable
import com.bitwiserain.kwitter.domain.model.Kweet
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

interface ListHomeKweets {
    fun getHomeKweetsInReverseChronologicalOrder(userId: Int): Iterable<Kweet>?
}

class ListHomeKweetsImpl : ListHomeKweets {
    override fun getHomeKweetsInReverseChronologicalOrder(userId: Int): Iterable<Kweet>? = transaction {
        val userExists = UserTable.select { UserTable.id.eq(userId) }
            .count() > 0

        if (!userExists) return@transaction null

        // TODO: Once Exposed supports union, use here instead of two separate queries
        val followsKweets = FollowsTable.join(KweetTable, JoinType.INNER, FollowsTable.targetId, KweetTable.authorId)
            .slice(KweetTable.columns)
            .select { FollowsTable.followerId.eq(userId) }
            .map { KweetTable.toKweet(it) }

        val selfKweets = KweetTable.select { KweetTable.authorId.eq(userId) }
            .mapNotNull { KweetTable.toKweet(it) }

        return@transaction followsKweets.plus(selfKweets).sortedByDescending { it.date }
    }
}
