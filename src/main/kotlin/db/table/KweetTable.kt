package com.bitwiserain.kwitter.db.table

import com.bitwiserain.kwitter.KWEET_MAX_LENGTH
import com.bitwiserain.kwitter.domain.model.Kweet
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.ResultRow
import java.time.Instant

object KweetTable : IntIdTable() {
    val authorId = reference("author_id", UserTable)
    val text = varchar("text", KWEET_MAX_LENGTH)
    val timestampMS = long("timestamp_ms")

    fun toKweet(r: ResultRow): Kweet = Kweet(
        id = r[id].value,
        authorId = r[authorId].value,
        text = r[text],
        date = Instant.ofEpochMilli(r[timestampMS])
    )
}
