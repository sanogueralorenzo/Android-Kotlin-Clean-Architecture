package com.sanogueralorenzo.posts.data.repository

import com.sanogueralorenzo.posts.data.datasource.PostCacheDataSource
import com.sanogueralorenzo.posts.data.datasource.PostRemoteDataSource
import com.sanogueralorenzo.posts.domain.model.Post
import com.sanogueralorenzo.posts.domain.repository.PostRepository
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
