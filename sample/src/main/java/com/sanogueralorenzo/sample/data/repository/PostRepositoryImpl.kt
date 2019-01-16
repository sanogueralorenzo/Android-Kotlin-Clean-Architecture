package com.sanogueralorenzo.sample.data.repository

import com.sanogueralorenzo.sample.data.datasource.PostCacheDataSource
import com.sanogueralorenzo.sample.data.datasource.PostRemoteDataSource
import com.sanogueralorenzo.sample.domain.model.Post
import com.sanogueralorenzo.sample.domain.repository.PostRepository
import io.reactivex.Single

class PostRepositoryImpl constructor(
    private val cacheDataSource: PostCacheDataSource,
    private val remoteDataSource: PostRemoteDataSource
) : PostRepository {

    override fun get(refresh: Boolean): Single<List<Post>> =
        when (refresh) {
            true -> remoteDataSource.get()
                .flatMap { cacheDataSource.set(it) }
            false -> cacheDataSource.get()
                .onErrorResumeNext { get(true) }
        }

    override fun get(postId: String, refresh: Boolean): Single<Post> =
        when (refresh) {
            true -> remoteDataSource.get(postId)
                .flatMap { cacheDataSource.set(it) }
            false -> cacheDataSource.get(postId)
                .onErrorResumeNext { get(postId, true) }
        }
}
