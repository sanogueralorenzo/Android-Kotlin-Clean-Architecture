package com.sanogueralorenzo.sample.domain

import com.sanogueralorenzo.sample.data.GifsRepository
import javax.inject.Inject

/**
 * Subjective:
 * Does it make sense to have an empty use case that just calls the repository?
 * Alternative, inject the repository directly to the ViewModel (until there is some business logic)
 */
class SearchGifsUseCase @Inject constructor(
    private val repository: GifsRepository
) {
    suspend fun run(searchTerm: String, offset: Int): List<Gif> =
        repository.search(searchTerm, offset)
}
