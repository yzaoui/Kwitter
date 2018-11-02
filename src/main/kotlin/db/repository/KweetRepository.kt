package com.bitwiserain.kwitter.db.repository

import com.bitwiserain.kwitter.db.table.KweetTable
import com.bitwiserain.kwitter.db.table.UserTable
import com.bitwiserain.kwitter.domain.model.Kweet
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.Instant

object KweetRepository {
    fun create(authorId: Int, kweetText: String) {
        transaction {
            KweetTable.insert {
                it[KweetTable.authorId] = EntityID(authorId, UserTable)
                it[KweetTable.text] = kweetText
                it[KweetTable.timestampMS] = Instant.now().toEpochMilli()
            }
        }
    }

    fun get(kweetId: Int): Kweet? = transaction {
        KweetTable.select { KweetTable.id.eq(kweetId) }
            .mapNotNull { KweetTable.toKweet(it) }
            .singleOrNull()
    }
}
