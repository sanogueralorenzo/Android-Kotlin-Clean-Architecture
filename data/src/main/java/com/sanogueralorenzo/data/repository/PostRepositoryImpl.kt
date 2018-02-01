package com.sanogueralorenzo.data.repository

import com.sanogueralorenzo.data.cache.Cache
import com.sanogueralorenzo.data.model.PostEntity
import com.sanogueralorenzo.data.model.PostMapper
import com.sanogueralorenzo.data.remote.PostsApi
import com.sanogueralorenzo.domain.model.Post
import com.sanogueralorenzo.domain.repository.PostRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepositoryImpl @Inject constructor(private val api: PostsApi,
                                             private val cache: Cache<List<PostEntity>>,
                                             private val mapper: PostMapper) : PostRepository {

    private val key = "Post List"

    override fun getRemote(): Single<List<Post>> = api.getPosts().doAfterSuccess { setCache(it) }.map { mapper.mapToDomain(it) }

    override fun getCache(): Single<List<Post>> = cache.load(key, listOf()).map { mapper.mapToDomain(it) }

    private fun setCache(list: List<PostEntity>) = cache.save(key, list).subscribe()
}
