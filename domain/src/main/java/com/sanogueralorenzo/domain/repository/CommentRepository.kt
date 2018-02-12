package com.sanogueralorenzo.domain.repository

import com.sanogueralorenzo.domain.model.Comment
import io.reactivex.Single

interface CommentRepository {

    val key: String

    fun get(postId: String, refresh: Boolean): Single<List<Comment>>
}
