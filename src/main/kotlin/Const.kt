package kwitter

const val USERNAME_REGEX = "[A-Za-z0-9_]{1,15}"

val RESERVED_USERNAMES = setOf("login", "logout", "signup")
