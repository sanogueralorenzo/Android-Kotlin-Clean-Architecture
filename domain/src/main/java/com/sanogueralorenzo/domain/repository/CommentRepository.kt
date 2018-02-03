package com.sanogueralorenzo.domain.repository

import com.sanogueralorenzo.domain.model.Comment
import io.reactivex.Flowable

interface CommentRepository {

    fun getComments(postId: String): Flowable<List<Comment>>
}
