package kwitter.domain.usecase

import kwitter.data.KweetRepository
import kwitter.data.model.Kweet

interface ListUserKweets {
    fun getKweetsInReverseChronologicalOrder(username: String): Iterable<Kweet>
}

class ListUserKweetsImpl(private val kweetRepo: KweetRepository) : ListUserKweets {
    override fun getKweetsInReverseChronologicalOrder(username: String): Iterable<Kweet> {
        return kweetRepo.getAllFrom(username).sortedByDescending { it.date }
    }
}
