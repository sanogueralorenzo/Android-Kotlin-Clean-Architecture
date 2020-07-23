package com.sanogueralorenzo.sample.datasource.remote

import com.sanogueralorenzo.sample.domain.Gif
import javax.inject.Inject

/**
 * Mapping from response to a basic domain object in the RemoteDataSource layer
 * This avoids the propagation of the response model from the data source layer
 */
class GifsRemoteDataSource @Inject constructor(
    private val service: GifService
) {

    suspend fun search(searchTerm: String, offset: Int): List<Gif> =
        service.searchGifs(searchTerm, offset)
            .toDomainModel()

    suspend fun loadTrending(offset: Int): List<Gif> =
        service.getTrendingGifs(offset)
            .toDomainModel()
}
