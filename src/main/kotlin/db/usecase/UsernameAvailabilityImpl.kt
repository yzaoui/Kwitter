package com.bitwiserain.kwitter.db.usecase

import com.bitwiserain.kwitter.db.table.UserTable
import com.bitwiserain.kwitter.domain.usecase.UsernameAvailability
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

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
