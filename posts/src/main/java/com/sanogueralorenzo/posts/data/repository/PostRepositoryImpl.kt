package com.sanogueralorenzo.posts.data.repository

import com.sanogueralorenzo.posts.data.cache.Cache
import com.sanogueralorenzo.posts.data.model.PostEntity
import com.sanogueralorenzo.posts.data.model.mapToDomain
import com.sanogueralorenzo.posts.data.remote.PostsApi
import com.sanogueralorenzo.posts.domain.model.Post
import com.sanogueralorenzo.posts.domain.repository.PostRepository
import io.reactivex.Single

class PostRepositoryImpl constructor(
    private val api: PostsApi,
    private val cache: Cache<List<PostEntity>>
) : PostRepository {

    var x = 0

    fun createPost(): Post {
        x += 1
        return Post("1", "1", "1", x.toString())
    }

    override val key = "Post List"

    override fun get(refresh: Boolean): Single<List<Post>> = Single.just(listOf(createPost()))
//        when (refresh) {
//            true -> api.getPosts()
//                .flatMap { set(it) }
//                .map { mapToDomain(it) }
//            false -> cache.load(key)
//                .map { mapToDomain(it) }
//                .onErrorResumeNext { get(true) }
//        }

    override fun get(postId: String, refresh: Boolean): Single<Post> = Single.just(createPost())
//        when (refresh) {
//            true -> api.getPost(postId)
//                .flatMap { set(it) }
//                .map { it.mapToDomain() }
//            false -> cache.load(key)
//                .map { it.first { it.id == postId } }
//                .map { it.mapToDomain() }
//                .onErrorResumeNext {
//                    get(postId, true)
//                }
//        }

    private fun set(list: List<PostEntity>) = cache.save(key, list)

    private fun set(entity: PostEntity) =
        cache.load(key)
            .map { it.filter { it.id != entity.id }.plus(entity) }
            .flatMap { set(it) }.map { entity }
}
