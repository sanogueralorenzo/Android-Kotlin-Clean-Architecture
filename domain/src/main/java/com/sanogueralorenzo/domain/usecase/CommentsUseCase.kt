package com.sanogueralorenzo.domain.usecase

import com.sanogueralorenzo.domain.model.Comment
import com.sanogueralorenzo.domain.repository.CommentRepository
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class CommentsUseCase @Inject constructor(private val repository: CommentRepository) : UseCase<Flowable<List<Comment>>> {

    lateinit var postId: String

    override fun execute(): Flowable<List<Comment>> = repository.getComments(postId)
}