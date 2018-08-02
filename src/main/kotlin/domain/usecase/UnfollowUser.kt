package kwitter.domain.usecase

import kwitter.data.UserRepository

object UnfollowUser {
    private val userRepo = UserRepository

    fun unfollow(usernameFollower: String, usernameToUnfollow: String) {
        userRepo.get(usernameFollower)?.let {
            it.follows.remove(usernameToUnfollow)
        }
    }
}
