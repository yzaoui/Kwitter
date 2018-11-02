package com.bitwiserain.kwitter.domain.usecase

import com.bitwiserain.kwitter.db.FollowsTable
import com.bitwiserain.kwitter.db.UserTable
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

interface UnfollowUser {
    fun unfollow(usernameFollower: String, usernameTarget: String)
}

class UnfollowUserImpl : UnfollowUser {
    private fun fetchIdFromUsername(username: String): Deferred<Int?> = GlobalScope.async {
        transaction {
            UserTable.slice(UserTable.id)
                .select { UserTable.username.eq(username) }
                .mapNotNull { it[UserTable.id].value }
                .singleOrNull()
        }
    }

    override fun unfollow(usernameFollower: String, usernameTarget: String) {
        val fetchFollowerId = fetchIdFromUsername(usernameFollower)
        val fetchTargetId = fetchIdFromUsername(usernameTarget)

        runBlocking {
            val followerId = fetchFollowerId.await()
            val targetId = fetchTargetId.await()

            // TODO: Currently, if something goes wrong, it will just silently fail. Consider propagating to the user
            if (followerId != null && targetId != null) {
                transaction {
                    FollowsTable.deleteWhere {
                        FollowsTable.followerId.eq(followerId).and(FollowsTable.targetId.eq(targetId))
                    }
                }
            }
        }
    }
}
