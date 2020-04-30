package com.sanogueralorenzo.sample.data

import com.sanogueralorenzo.sample.datasource.remote.GifsRemoteDataSource
import com.sanogueralorenzo.sample.domain.Gif
import io.reactivex.Single
import javax.inject.Inject

class GifsRepository @Inject constructor(
    private val remoteDataSource: GifsRemoteDataSource
) {

    fun search(searchTerm: String, offset: Int): Single<List<Gif>> =
        remoteDataSource.search(searchTerm, offset)

    fun loadTrending(offset: Int): Single<List<Gif>> =
        remoteDataSource.loadTrending(offset)
}
