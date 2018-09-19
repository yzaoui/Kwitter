package com.bitwiserain.kwitter.domain.usecase

import com.bitwiserain.kwitter.data.FollowsTable
import com.bitwiserain.kwitter.data.UserTable
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.alias
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

interface CheckFollow {
    fun follows(usernameFollower: String, usernameTarget: String): Boolean
}

class CheckFollowImpl : CheckFollow {
    override fun follows(usernameFollower: String, usernameTarget: String): Boolean {
        val count = transaction {
            val follower = UserTable.alias("follower")
            val target = UserTable.alias("target")
            FollowsTable.join(follower, JoinType.INNER, FollowsTable.followerId, follower[UserTable.id])
                .join(target, JoinType.INNER, FollowsTable.targetId, target[UserTable.id])
                .select { follower[UserTable.username].eq(usernameFollower).and(target[UserTable.username].eq(usernameTarget)) }
                .count()
        }

        return count > 0
    }
}
