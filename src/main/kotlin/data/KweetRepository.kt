package kwitter.data

import kwitter.data.model.Kweet
import java.time.Instant

object KweetRepository {
    private val kweets: MutableMap<String, Kweet> = mutableMapOf()

    fun create(username: String, text: String) {
        val id = kweets.size.toString()
        kweets[id] = (Kweet(
            id = id,
            username = username,
            text = text,
            date = Instant.now()
        ))
    }

    fun get(kweetId: String): Kweet? = kweets[kweetId]
    fun getAll(): Iterable<Kweet> = kweets.values.asIterable()
    fun getAllFrom(username: String): Iterable<Kweet> = kweets.values.filter { it.username == username }.asIterable()
}
