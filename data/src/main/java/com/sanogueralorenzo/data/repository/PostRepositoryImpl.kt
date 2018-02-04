package com.sanogueralorenzo.data.repository

import com.sanogueralorenzo.data.cache.Cache
import com.sanogueralorenzo.data.model.PostEntity
import com.sanogueralorenzo.data.model.PostMapper
import com.sanogueralorenzo.data.remote.PostsApi
import com.sanogueralorenzo.domain.model.Post
import com.sanogueralorenzo.domain.repository.PostRepository
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepositoryImpl @Inject constructor(private val api: PostsApi,
                                             private val cache: Cache<List<PostEntity>>,
                                             private val mapper: PostMapper) : PostRepository {
    private val key = "Post List"

    override fun getPosts(): Flowable<List<Post>> = Single.concat(getCache(), getRemote())

    override fun getPost(postId: String): Flowable<Post> = Single.concat(getCache(postId), getRemote(postId))

    private fun getRemote(): Single<List<Post>> = api.getPosts().flatMap { setCache(it) }.map { mapper.mapToDomain(it) }

    private fun getCache(): Single<List<Post>> = cache.load(key, emptyList()).map { mapper.mapToDomain(it) }

    private fun getRemote(postId: String): Single<Post> = api.getPost(postId).flatMap { setCache(it) }.map { mapper.mapToDomain(it) }

    private fun getCache(postId: String): Single<Post> = cache.load(key, emptyList()).map { it.first { it.id == postId } }.map { mapper.mapToDomain(it) }

    private fun setCache(list: List<PostEntity>) = cache.save(key, list)

    private fun setCache(entity: PostEntity) = cache.load(key, emptyList()).map { it.filter { it.id != entity.id }.plus(entity) }.flatMap { setCache(it) }.map { entity }
}
