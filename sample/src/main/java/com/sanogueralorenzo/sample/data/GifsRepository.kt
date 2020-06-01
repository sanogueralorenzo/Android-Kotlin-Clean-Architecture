package com.sanogueralorenzo.sample.data

import com.sanogueralorenzo.sample.datasource.remote.GifsRemoteDataSource
import com.sanogueralorenzo.sample.domain.Gif
import io.reactivex.Single
import javax.inject.Inject

/**
 * Subjective:
 * Does it make sense to have an empty repository that just calls the remote data source?
 * Is it better to leave it like this or create the repository when it is needed? (caching added)
 */
class GifsRepository @Inject constructor(
    private val remoteDataSource: GifsRemoteDataSource
) {

    fun search(searchTerm: String, offset: Int): Single<List<Gif>> =
        remoteDataSource.search(searchTerm, offset)

    fun loadTrending(offset: Int): Single<List<Gif>> =
        remoteDataSource.loadTrending(offset)
}
