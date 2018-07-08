package kwitter.data

import kwitter.data.model.User

object UserRepository {
    private val users: MutableMap<String, User> = mutableMapOf()

    //TODO: Temp data
    init {
        users["username"] = User("username", "password", "Display_Name", "email@example.com")
    }

    fun create(user: User) {
        users[user.username] = user
    }

    fun get(username: String): User? {
        return users[username]
    }
}
