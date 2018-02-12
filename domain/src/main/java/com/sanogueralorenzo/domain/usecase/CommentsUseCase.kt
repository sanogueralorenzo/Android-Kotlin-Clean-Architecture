package com.sanogueralorenzo.domain.usecase

import com.sanogueralorenzo.domain.model.Comment
import com.sanogueralorenzo.domain.repository.CommentRepository
import io.reactivex.Single
import javax.inject.Inject

class CommentsUseCase @Inject constructor(private val repository: CommentRepository) {

    fun get(postId: String, refresh: Boolean): Single<List<Comment>> = repository.get(postId, refresh)
}
