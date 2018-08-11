package kwitter

const val USERNAME_REGEX = "[A-Za-z0-9_]{1,15}"
const val MAX_KWEET_LENGTH = 280

val RESERVED_USERNAMES = setOf("login", "logout", "signup")
