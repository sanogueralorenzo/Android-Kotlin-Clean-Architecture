package com.sanogueralorenzo.sample.datasource.remote

import com.sanogueralorenzo.sample.data.datasource.CommentRemoteDataSource
import com.sanogueralorenzo.sample.datasource.model.mapToDomain
import com.sanogueralorenzo.sample.domain.model.Comment
import io.reactivex.Single

class CommentRemoteDataSourceImpl constructor(
    private val api: CommentsApi
) : CommentRemoteDataSource {

    override fun get(postId: String): Single<List<Comment>> =
        api.getComments(postId)
            .map { it.mapToDomain() }
}
