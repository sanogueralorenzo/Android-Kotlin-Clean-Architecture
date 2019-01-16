package com.sanogueralorenzo.sample.domain.usecase

import com.sanogueralorenzo.sample.domain.model.Comment
import com.sanogueralorenzo.sample.domain.repository.CommentRepository
import io.reactivex.Single

class CommentsUseCase constructor(private val commentRepository: CommentRepository) {

    fun get(postId: String, refresh: Boolean): Single<List<Comment>> =
        commentRepository.get(postId, refresh)
}
