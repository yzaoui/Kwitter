package kwitter.domain.usecase

import kwitter.data.UserRepository

interface CheckFollow {
    fun follows(usernameFollower: String, usernameTarget: String): Boolean
}

class CheckFollowImpl(private val userRepo: UserRepository) : CheckFollow {
    override fun follows(usernameFollower: String, usernameTarget: String): Boolean {
        return (userRepo.get(usernameFollower) ?: return false).follows.contains(usernameTarget)
    }
}
