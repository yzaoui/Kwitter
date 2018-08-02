package kwitter.domain.usecase

import kwitter.data.UserRepository

object CheckFollow {
    private val userRepo = UserRepository

    fun follows(usernameFollower: String, usernameTarget: String): Boolean {
        return (userRepo.get(usernameFollower) ?: return false).follows.contains(usernameTarget)
    }
}
