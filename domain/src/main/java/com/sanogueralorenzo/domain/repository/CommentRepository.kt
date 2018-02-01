package com.sanogueralorenzo.domain.repository

import com.sanogueralorenzo.domain.model.Comment
import io.reactivex.Single

interface CommentRepository {

    fun getRemote(postId: String): Single<List<Comment>>

    fun getCache(postId: String): Single<List<Comment>>
}
