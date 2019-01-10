package com.sanogueralorenzo.posts.datasource.cache

import com.sanogueralorenzo.cache.Cache
import com.sanogueralorenzo.posts.data.datasource.CommentCacheDataSource
import com.sanogueralorenzo.posts.domain.model.Comment
import io.reactivex.Single

class CommentCacheDataSourceImpl constructor(
    private val cache: Cache<List<Comment>>
) : CommentCacheDataSource {

    val key = "Comment List"

    override fun get(postId: String): Single<List<Comment>> =
        cache.load(key + postId)

    override fun set(postId: String, list: List<Comment>): Single<List<Comment>> =
        cache.save(key + postId, list)
}
