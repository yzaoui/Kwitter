package com.bitwiserain.kwitter.db

import org.jetbrains.exposed.sql.Table

object FollowsTable : Table() {
    val followerId = reference("follower_id", UserTable).primaryKey()
    val targetId = reference("target_id", UserTable).primaryKey()
    val timestampMS = long("timestamp_ms")
}
