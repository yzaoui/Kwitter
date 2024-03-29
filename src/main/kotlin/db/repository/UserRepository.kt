package com.bitwiserain.kwitter.db.repository

import com.bitwiserain.kwitter.db.table.UserTable
import com.bitwiserain.kwitter.domain.model.User
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

object UserRepository {
    fun create(username: String, passwordHash: ByteArray, displayName: String, email: String, profilePictureURL: String): Int = transaction {
        UserTable.insertAndGetId {
            it[UserTable.username] = username
            it[UserTable.passwordHash] = passwordHash
            it[UserTable.displayName] = displayName
            it[UserTable.email] = email
            it[UserTable.profilePictureURL] = profilePictureURL
        }.value
    }

    fun get(userId: Int): User? = transaction {
        UserTable.select { UserTable.id.eq(userId) }
            .mapNotNull {
                User(
                    it[UserTable.id].value,
                    it[UserTable.username],
                    it[UserTable.passwordHash],
                    it[UserTable.displayName],
                    "fake@email.com",
                    it[UserTable.profilePictureURL]
                )
            }
            .singleOrNull()
    }

    fun getByUsername(username: String): User? = transaction {
        UserTable.select { UserTable.username.eq(username) }
            .mapNotNull {
                User(
                    it[UserTable.id].value,
                    it[UserTable.username],
                    it[UserTable.passwordHash],
                    it[UserTable.displayName],
                    "fake@email.com",
                    it[UserTable.profilePictureURL]
                )
            }
            .singleOrNull()
    }

    fun changeProfilePictureURL(userId: Int, newURL: String): Unit = transaction {
        UserTable.update({ UserTable.id.eq(userId) }) {
            it[UserTable.profilePictureURL] = newURL
        }
    }
}
