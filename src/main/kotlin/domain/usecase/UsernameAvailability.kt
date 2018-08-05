package kwitter.domain.usecase

import kwitter.RESERVED_USERNAMES
import kwitter.data.UserRepository

object UsernameAvailability {
    private val userRepo = UserRepository
    private val reservedUsername = RESERVED_USERNAMES

    fun check(username: String): Boolean {
        val takenUsernames = UserRepository.getUsernames()

        return username !in takenUsernames.plus(reservedUsername)
    }
}
