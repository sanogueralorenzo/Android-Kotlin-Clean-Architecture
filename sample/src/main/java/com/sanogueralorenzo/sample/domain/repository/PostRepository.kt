package com.sanogueralorenzo.sample.domain.repository

import com.sanogueralorenzo.sample.domain.model.Post
import io.reactivex.Single

interface PostRepository {

    fun get(refresh: Boolean): Single<List<Post>>

    fun get(postId: String, refresh: Boolean): Single<Post>
}
