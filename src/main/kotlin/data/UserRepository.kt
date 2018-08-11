package kwitter.data

import kwitter.data.model.User

object UserRepository {
    private val users: MutableMap<String, User> = mutableMapOf()

    fun create(user: User) {
        users[user.username] = user
    }

    fun get(username: String): User? {
        return users[username]
    }

    fun getUsernames(): Set<String> = users.keys
}
