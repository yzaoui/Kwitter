package kwitter

const val USERNAME_MAX_LENGTH = 15
const val USERNAME_REGEX = "[A-Za-z0-9_]{1,$USERNAME_MAX_LENGTH}"
const val DISPLAY_NAME_MAX_LENGTH = 255
const val EMAIL_MAX_LENGTH = 256
const val KWEET_MAX_LENGTH = 280

val RESERVED_USERNAMES = setOf("login", "logout", "signup", "assets")
