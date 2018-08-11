package kwitter.data

import at.favre.lib.crypto.bcrypt.BCrypt
import kwitter.DISPLAY_NAME_MAX_LENGTH
import kwitter.USERNAME_MAX_LENGTH
import kwitter.data.model.User
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object UserRepository {
    fun create(user: User) = transaction {
        UserTable.insert {
            it[username] = user.username
            it[passwordHash] = BCrypt.withDefaults().hash(12, user.passwordHash)
            it[displayName] = user.displayName
            it[profilePictureURL] = user.profilePictureURL
        }
    }

    fun get(username: String): User? = transaction {
        UserTable.select { UserTable.username.eq(username) }
            .mapNotNull {
                User(it[UserTable.username], it[UserTable.passwordHash], it[UserTable.displayName], "fake@email.com", it[UserTable.profilePictureURL], mutableSetOf())
            }
            .singleOrNull()
    }

    fun getUsernames(): Collection<String> = transaction {
        UserTable.slice(UserTable.username)
            .selectAll()
            .map { it[UserTable.username] }
    }
}

object UserTable : IntIdTable() {
    val username = varchar("username", USERNAME_MAX_LENGTH).uniqueIndex()
    val passwordHash = binary("password_hash", 60)
    val displayName = varchar("display_name", DISPLAY_NAME_MAX_LENGTH)
    val profilePictureURL = varchar("profile_picture_url", 255)
}
