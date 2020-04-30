package com.sanogueralorenzo.sample.domain

import com.sanogueralorenzo.sample.data.GifsRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Subjective:
 * Does it make sense to have an empty use case that just calls the repository?
 * Alternative, inject the repository directly to the ViewModel (until there is some business logic)
 */
class TrendingGifsUseCase @Inject constructor(
    private val repository: GifsRepository
) : (Int) -> Single<List<Gif>> {

    override fun invoke(offset: Int): Single<List<Gif>> = repository.loadTrending(offset)
}
