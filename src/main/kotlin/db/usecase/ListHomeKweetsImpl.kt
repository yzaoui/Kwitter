package com.bitwiserain.kwitter.db.usecase

import com.bitwiserain.kwitter.db.table.FollowsTable
import com.bitwiserain.kwitter.db.table.KweetTable
import com.bitwiserain.kwitter.db.table.UserTable
import com.bitwiserain.kwitter.domain.model.Kweet
import com.bitwiserain.kwitter.domain.usecase.ListHomeKweets
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

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

        return@transaction followsKweets.asSequence()
            .plus(selfKweets)
            .sortedByDescending { it.date }.toList()
    }
}
