package kwitter.data.model

import java.util.*

// equals() and hashCode() are overridden due to passwordHash being a ByteArray
data class User(
    val username: String,
    val passwordHash: ByteArray,
    val displayName: String,
    val email: String,
    var profilePictureURL: String,
    val follows: MutableSet<String> = mutableSetOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (username != other.username) return false
        if (!Arrays.equals(passwordHash, other.passwordHash)) return false
        if (displayName != other.displayName) return false
        if (email != other.email) return false
        if (profilePictureURL != other.profilePictureURL) return false
        if (follows != other.follows) return false

        return true
    }

    override fun hashCode(): Int {
        var result = username.hashCode()
        result = 31 * result + Arrays.hashCode(passwordHash)
        result = 31 * result + displayName.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + profilePictureURL.hashCode()
        result = 31 * result + follows.hashCode()
        return result
    }
}
