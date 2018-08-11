package kwitter.domain.usecase

import kwitter.data.UserRepository

interface UnfollowUser {
    fun unfollow(usernameFollower: String, usernameToUnfollow: String)
}

class UnfollowUserImpl(private val userRepo: UserRepository) : UnfollowUser {
    override fun unfollow(usernameFollower: String, usernameToUnfollow: String) {
        userRepo.get(usernameFollower)?.follows?.remove(usernameToUnfollow)
    }
}
