package com.sanogueralorenzo.sample.datasource.remote

import com.sanogueralorenzo.sample.domain.Gif
import io.reactivex.Single
import javax.inject.Inject

/**
 * Mapping from response to a basic domain object in the RemoteDataSource layer
 * This avoids the propagation of the response model from the data source layer
 */
class GifsRemoteDataSource @Inject constructor(
    private val service: GifService
) {

    fun search(searchTerm: String, offset: Int): Single<List<Gif>> =
        service.searchGifs(searchTerm, offset)
            .map { it.toDomainModel() }

    fun loadTrending(offset: Int): Single<List<Gif>> = service.getTrendingGifs(offset)
        .map { it.toDomainModel() }
}
