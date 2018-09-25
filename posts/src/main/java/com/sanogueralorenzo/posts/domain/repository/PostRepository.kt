package com.sanogueralorenzo.posts.domain.repository

import com.sanogueralorenzo.posts.domain.model.Post
import io.reactivex.Single

interface PostRepository {

    fun get(refresh: Boolean): Single<List<Post>>

    fun get(postId: String, refresh: Boolean): Single<Post>
}
