package com.sanogueralorenzo.data.repository

import com.sanogueralorenzo.data.cache.Cache
import com.sanogueralorenzo.data.model.CommentEntity
import com.sanogueralorenzo.data.model.CommentMapper
import com.sanogueralorenzo.data.remote.CommentsApi
import com.sanogueralorenzo.domain.model.Comment
import com.sanogueralorenzo.domain.repository.CommentRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommentRepositoryImpl @Inject constructor(
    private val api: CommentsApi,
    private val cache: Cache<List<CommentEntity>>,
    private val mapper: CommentMapper
) : CommentRepository {
    override val key = "Comment List"

    override fun get(postId: String, refresh: Boolean): Single<List<Comment>> = when (refresh) {
        true -> api.getComments(postId).flatMap { set(postId, it) }.map { mapper.mapToDomain(it) }
        false -> cache.load(key + postId).map { mapper.mapToDomain(it) }.onErrorResumeNext { get(postId, true) }
    }

    private fun set(postId: String, list: List<CommentEntity>) = cache.save(key + postId, list)
}
