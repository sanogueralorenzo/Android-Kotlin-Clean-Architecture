package com.sanogueralorenzo.data.repository

import com.sanogueralorenzo.data.cache.Cache
import com.sanogueralorenzo.data.model.CommentEntity
import com.sanogueralorenzo.data.model.CommentMapper
import com.sanogueralorenzo.data.remote.CommentsApi
import com.sanogueralorenzo.domain.model.Comment
import com.sanogueralorenzo.domain.repository.CommentRepository
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommentRepositoryImpl @Inject constructor(private val api: CommentsApi,
                                                private val cache: Cache<List<CommentEntity>>,
                                                private val mapper: CommentMapper) : CommentRepository {
    private val key = "Comment List"

    override fun getComments(postId: String): Flowable<List<Comment>> = Single.concat(getCache(postId), getRemote(postId))

    private fun getRemote(postId: String): Single<List<Comment>> = api.getComments(postId).flatMap { setCache(postId, it) }.map { mapper.mapToDomain(it) }

    private fun getCache(postId: String): Single<List<Comment>> = cache.load(key + postId, emptyList()).map { mapper.mapToDomain(it) }

    private fun setCache(postId: String, list: List<CommentEntity>) = cache.save(key + postId, list)
}
