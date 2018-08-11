package kwitter.domain.usecase

import kwitter.data.UserRepository

interface FollowUser {
    fun follow(usernameFollower: String, usernameToFollow: String)
}

class FollowUserImpl(private val userRepo: UserRepository) : FollowUser {
    override fun follow(usernameFollower: String, usernameToFollow: String) {
        userRepo.get(usernameFollower)?.follows?.add(usernameToFollow)
    }
}
