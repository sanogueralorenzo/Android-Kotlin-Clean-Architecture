package com.sanogueralorenzo.domain.repository

import com.sanogueralorenzo.domain.model.Post
import io.reactivex.Flowable

interface PostRepository {

    fun getPosts(): Flowable<List<Post>>

    fun getPost(postId: String): Flowable<Post>
}
