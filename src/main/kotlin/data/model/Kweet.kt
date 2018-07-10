package kwitter.data.model

import java.time.Instant

data class Kweet(
    val id: Int,
    val username: String,
    val text: String,
    val date: Instant
)
