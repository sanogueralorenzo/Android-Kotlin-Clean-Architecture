package com.sanogueralorenzo.sample.data.repository

import com.sanogueralorenzo.sample.data.datasource.CommentCacheDataSource
import com.sanogueralorenzo.sample.data.datasource.CommentRemoteDataSource
import com.sanogueralorenzo.sample.domain.model.Comment
import com.sanogueralorenzo.sample.domain.repository.CommentRepository
import io.reactivex.Single

class CommentRepositoryImpl constructor(
    private val cacheDataSource: CommentCacheDataSource,
    private val remoteDataSource: CommentRemoteDataSource
) : CommentRepository {

    override fun get(postId: String, refresh: Boolean): Single<List<Comment>> =
        when (refresh) {
            true -> remoteDataSource.get(postId)
                .flatMap { cacheDataSource.set(postId, it) }
            false -> cacheDataSource.get(postId)
                .onErrorResumeNext { get(postId, true) }
        }
}
