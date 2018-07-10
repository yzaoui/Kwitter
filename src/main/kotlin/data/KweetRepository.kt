package kwitter.data

import kwitter.data.model.Kweet
import java.time.Instant

object KweetRepository {
    private val kweets = mutableListOf<Kweet>()

    fun create(username: String, text: String) {
        kweets.add(Kweet(
            id = kweets.size.toString(),
            username = username,
            text = text,
            date = Instant.now()
        ))
    }

    fun get(kweetId: Int): Kweet? = kweets[kweetId]
    fun getAll(): List<Kweet> = kweets
}
