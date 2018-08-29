package com.sanogueralorenzo.data.model

import com.sanogueralorenzo.domain.model.Post
import com.squareup.moshi.Json
import javax.inject.Inject

data class PostEntity(
    @Json(name = "userId") val userId: String,
    @Json(name = "id") val id: String,
    @Json(name = "title") val title: String,
    @Json(name = "body") val body: String
)

class PostMapper @Inject constructor() {

    fun mapToDomain(entity: PostEntity): Post = Post(
        entity.userId,
        entity.id,
        entity.title,
        entity.body
    )

    fun mapToDomain(list: List<PostEntity>): List<Post> = list.map { mapToDomain(it) }

    fun mapToEntity(post: Post): PostEntity = PostEntity(
        post.userId,
        post.id,
        post.title,
        post.body
    )

    fun mapToEntity(list: List<Post>): List<PostEntity> = list.map { mapToEntity(it) }
}
