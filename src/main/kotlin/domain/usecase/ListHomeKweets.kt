package kwitter.domain.usecase

import kwitter.data.KweetRepository
import kwitter.data.UserRepository
import kwitter.data.model.Kweet

interface ListHomeKweets {
    fun getKweetsInReverseChronologicalOrder(username: String): Iterable<Kweet>?
}

class ListHomeKweetsImpl(private val kweetRepo: KweetRepository, private val userRepo: UserRepository) : ListHomeKweets {
    override fun getKweetsInReverseChronologicalOrder(username: String): Iterable<Kweet>? {
        val user = userRepo.get(username) ?: return null

        return kweetRepo.getAll()
            .filter { user.follows.contains(it.username) || user.username == it.username }
            .sortedByDescending { it.date }
    }
}
