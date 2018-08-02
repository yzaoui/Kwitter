package kwitter.domain.usecase

import kwitter.data.KweetRepository
import kwitter.data.model.Kweet

object ListUserKweets {
    private val kweetRepo = KweetRepository

    fun getKweets(username: String): Iterable<Kweet> {
        return kweetRepo.getAllFrom(username)
    }
}
