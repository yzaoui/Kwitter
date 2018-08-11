package kwitter.domain.usecase

import kwitter.data.UserRepository

interface UsernameAvailability {
    fun check(username: String): Boolean
}

class UsernameAvailabilityImpl(private val userRepo: UserRepository, private val reservedUsernames: Set<String>) : UsernameAvailability {
    override fun check(username: String): Boolean {
        val takenUsernames = UserRepository.getUsernames()

        return username !in takenUsernames.plus(reservedUsernames)
    }
}
