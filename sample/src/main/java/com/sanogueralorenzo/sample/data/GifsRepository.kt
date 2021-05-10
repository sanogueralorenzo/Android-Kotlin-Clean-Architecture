package com.sanogueralorenzo.sample.data

import com.sanogueralorenzo.sample.datasource.remote.GifsRemoteDataSource
import com.sanogueralorenzo.sample.domain.Gif

/**
 * Subjective:
 * Does it make sense to have an empty repository that just calls the remote data source?
 * Is it better to leave it like this or create the repository when it is needed? (caching added)
 */
class GifsRepository constructor(
    private val remoteDataSource: GifsRemoteDataSource = GifsRemoteDataSource()
) {

    suspend fun search(searchTerm: String, offset: Int): List<Gif> =
        remoteDataSource.search(searchTerm, offset)

    suspend fun loadTrending(offset: Int): List<Gif> =
        remoteDataSource.loadTrending(offset)
}
