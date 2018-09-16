package com.sanogueralorenzo.posts.domain.usecase

import com.sanogueralorenzo.posts.domain.model.Comment
import com.sanogueralorenzo.posts.domain.repository.CommentRepository
import io.reactivex.Single

class CommentsUseCase constructor(private val repository: CommentRepository) {

    fun get(postId: String, refresh: Boolean): Single<List<Comment>> =
        repository.get(postId, refresh)
}
