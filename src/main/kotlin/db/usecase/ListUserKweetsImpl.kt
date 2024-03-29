package com.bitwiserain.kwitter.db.usecase

import com.bitwiserain.kwitter.db.table.KweetTable
import com.bitwiserain.kwitter.db.table.UserTable
import com.bitwiserain.kwitter.domain.model.Kweet
import com.bitwiserain.kwitter.domain.usecase.ListUserKweets
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class ListUserKweetsImpl : ListUserKweets {
    override fun getKweetsInReverseChronologicalOrder(username: String): Iterable<Kweet> = transaction {
        val userId = UserTable.select { UserTable.username.eq(username) }
            .map { it[UserTable.id] }
            .singleOrNull()
            ?.value

        // TODO: Currently silently fails if user doesn't exist
        if (userId != null) {
            KweetTable.select { KweetTable.authorId.eq(userId) }
                .orderBy(KweetTable.timestampMS to false)
                .map { KweetTable.toKweet(it) }
        } else {
            emptyList()
        }
    }
}
