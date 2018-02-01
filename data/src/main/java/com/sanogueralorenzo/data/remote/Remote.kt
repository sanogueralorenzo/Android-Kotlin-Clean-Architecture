package com.sanogueralorenzo.data.remote

import com.sanogueralorenzo.data.model.CommentEntity
import com.sanogueralorenzo.data.model.PostEntity
import com.sanogueralorenzo.data.model.UserEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostsApi {

    @GET("posts/")
    fun getPosts(): Single<List<PostEntity>>
}

interface UsersApi {

    @GET("users/")
    fun getUsers(): Single<List<UserEntity>>

    @GET("users/{id}")
    fun getUser(@Path("id") userId: String): Single<UserEntity>
}

interface CommentsApi {

    @GET("comments/")
    fun getComments(@Query("postId") postId: String): Single<List<CommentEntity>>
}
