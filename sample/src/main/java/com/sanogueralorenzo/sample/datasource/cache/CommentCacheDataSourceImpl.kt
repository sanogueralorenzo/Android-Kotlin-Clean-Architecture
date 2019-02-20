package com.sanogueralorenzo.sample.datasource.cache

import com.sanogueralorenzo.cache.ReactiveCache
import com.sanogueralorenzo.sample.data.datasource.CommentCacheDataSource
import com.sanogueralorenzo.sample.domain.model.Comment
import io.reactivex.Single

class CommentCacheDataSourceImpl constructor(
    private val cache: ReactiveCache<List<Comment>>
) : CommentCacheDataSource {

    val key = "Comment List"

    override fun get(postId: String): Single<List<Comment>> =
        cache.load(key + postId)

    override fun set(postId: String, list: List<Comment>): Single<List<Comment>> =
        cache.save(key + postId, list)
}
