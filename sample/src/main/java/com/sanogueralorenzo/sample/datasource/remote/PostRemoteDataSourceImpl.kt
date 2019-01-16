package com.sanogueralorenzo.sample.datasource.remote

import com.sanogueralorenzo.sample.data.datasource.PostRemoteDataSource
import com.sanogueralorenzo.sample.datasource.model.mapToDomain
import com.sanogueralorenzo.sample.domain.model.Post
import io.reactivex.Single

class PostRemoteDataSourceImpl constructor(
    private val api: PostsApi
) : PostRemoteDataSource {

    override fun get(): Single<List<Post>> =
        api.getPosts()
            .map { it.mapToDomain() }

    override fun get(postId: String): Single<Post> =
        api.getPost(postId)
            .map { it.mapToDomain() }
}
