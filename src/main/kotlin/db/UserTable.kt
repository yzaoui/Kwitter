package com.bitwiserain.kwitter.db

import com.bitwiserain.kwitter.DISPLAY_NAME_MAX_LENGTH
import com.bitwiserain.kwitter.EMAIL_MAX_LENGTH
import com.bitwiserain.kwitter.USERNAME_MAX_LENGTH
import org.jetbrains.exposed.dao.IntIdTable

object UserTable : IntIdTable() {
    val username = varchar("username", USERNAME_MAX_LENGTH).uniqueIndex()
    val passwordHash = binary("password_hash", 60)
    val displayName = varchar("display_name", DISPLAY_NAME_MAX_LENGTH)
    val email = varchar("email", EMAIL_MAX_LENGTH)
    val profilePictureURL = varchar("profile_picture_url", 255)
}
