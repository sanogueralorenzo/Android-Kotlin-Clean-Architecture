package com.sanogueralorenzo.posts.data.repository

import com.sanogueralorenzo.cache.Cache
import com.sanogueralorenzo.posts.data.model.CommentEntity
import com.sanogueralorenzo.posts.data.model.mapToDomain
import com.sanogueralorenzo.posts.data.remote.CommentsApi
import com.sanogueralorenzo.posts.domain.model.Comment
import com.sanogueralorenzo.posts.domain.repository.CommentRepository
import io.reactivex.Single

class CommentRepositoryImpl constructor(
    private val api: CommentsApi,
    private val cache: Cache<List<CommentEntity>>
) : CommentRepository {

    val key = "Comment List"

    override fun get(postId: String, refresh: Boolean): Single<List<Comment>> =
        when (refresh) {
            true -> api.getComments(postId)
                .flatMap { set(postId, it) }
                .map { it.mapToDomain() }
            false -> cache.load(key + postId)
                .map { it.mapToDomain() }
                .onErrorResumeNext { get(postId, true) }
        }

    private fun set(postId: String, list: List<CommentEntity>) = cache.save(key + postId, list)
}
