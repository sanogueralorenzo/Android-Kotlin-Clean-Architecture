package com.sanogueralorenzo.posts.datasource.remote

import com.sanogueralorenzo.posts.data.datasource.CommentRemoteDataSource
import com.sanogueralorenzo.posts.datasource.model.mapToDomain
import com.sanogueralorenzo.posts.domain.model.Comment
import io.reactivex.Single

class CommentRemoteDataSourceImpl constructor(
    private val api: CommentsApi
) : CommentRemoteDataSource {

    override fun get(postId: String): Single<List<Comment>> =
        api.getComments(postId)
            .map { it.mapToDomain() }
}
