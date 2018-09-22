package com.bitwiserain.kwitter.data

import com.bitwiserain.kwitter.KWEET_MAX_LENGTH
import com.bitwiserain.kwitter.domain.model.Kweet
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.ResultRow
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

object KweetTable : IntIdTable() {
    val authorId = reference("author_id", UserTable)
    val text = varchar("text", KWEET_MAX_LENGTH)
    val timestampMS = long("timestamp_ms")

    fun toKweet(r: ResultRow): Kweet = Kweet(
        id = r[KweetTable.id].value,
        authorId = r[KweetTable.authorId].value,
        text = r[KweetTable.text],
        date = Instant.ofEpochMilli(r[KweetTable.timestampMS])
    )
}
