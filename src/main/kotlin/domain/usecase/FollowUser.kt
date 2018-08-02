package kwitter.domain.usecase

import kwitter.data.UserRepository

object FollowUser {
    private val userRepo = UserRepository

    fun follow(usernameFollower: String, usernameToFollow: String) {
        userRepo.get(usernameFollower)?.let {
            it.follows.add(usernameToFollow)
        }
    }
}
