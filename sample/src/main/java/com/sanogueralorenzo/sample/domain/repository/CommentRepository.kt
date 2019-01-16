package com.sanogueralorenzo.sample.domain.repository

import com.sanogueralorenzo.sample.domain.model.Comment
import io.reactivex.Single

interface CommentRepository {

    fun get(postId: String, refresh: Boolean): Single<List<Comment>>
}
