package com.sanogueralorenzo.posts.data.model

import com.sanogueralorenzo.posts.domain.model.Post
import com.squareup.moshi.Json

data class PostEntity(
    @Json(name = "userId") val userId: String,
    @Json(name = "id") val id: String,
    @Json(name = "title") val title: String,
    @Json(name = "body") val body: String
)

fun PostEntity.mapToDomain(): Post = Post(userId, id, title, body)

fun mapToDomain(list: List<PostEntity>): List<Post> = list.map { it.mapToDomain() }
