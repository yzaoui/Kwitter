package com.bitwiserain.kwitter.domain.usecase

import com.bitwiserain.kwitter.db.UserTable
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

interface UsernameAvailability {
    fun check(username: String): Boolean
}

class UsernameAvailabilityImpl(private val reservedUsernames: Collection<String>) : UsernameAvailability {
    override fun check(username: String): Boolean {
        val takenUsernames = transaction {
            UserTable.slice(UserTable.username)
                .selectAll()
                .map { it[UserTable.username] }
        }

        return username !in takenUsernames.plus(reservedUsernames)
    }
}
