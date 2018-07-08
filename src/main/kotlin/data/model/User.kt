package kwitter.data.model

data class User(
    val username: String,
    val passwordHash: String,
    val displayName: String,
    val email: String
)
