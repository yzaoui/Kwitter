package kwitter.domain.usecase

import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking
import kwitter.data.FollowsTable
import kwitter.data.UserTable
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.Instant

interface FollowUser {
    fun follow(usernameFollower: String, usernameTarget: String)
}

class FollowUserImpl : FollowUser {
    private fun fetchIdFromUsername(username: String): Deferred<Int?> = async {
        transaction {
            UserTable.slice(UserTable.id)
                .select { UserTable.username.eq(username) }
                .mapNotNull { it[UserTable.id].value }
                .singleOrNull()
        }
    }

    override fun follow(usernameFollower: String, usernameTarget: String) {
        val fetchFollowerId = fetchIdFromUsername(usernameFollower)
        val fetchTargetId = fetchIdFromUsername(usernameTarget)

        runBlocking {
            val followerId = fetchFollowerId.await()
            val targetId = fetchTargetId.await()

            // TODO: Currently, if something goes wrong, it will just silently fail. Consider propagating to the user
            if (followerId != null && targetId != null) {
                transaction {
                    FollowsTable.insert {
                        it[FollowsTable.followerId] = EntityID(followerId, UserTable)
                        it[FollowsTable.targetId] = EntityID(targetId, UserTable)
                        it[FollowsTable.timestampMS] = Instant.now().toEpochMilli()
                    }
                }
            }
        }
    }
}
