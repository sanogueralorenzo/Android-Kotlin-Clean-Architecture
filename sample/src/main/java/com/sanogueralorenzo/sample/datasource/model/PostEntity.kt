package com.sanogueralorenzo.sample.datasource.model

import com.sanogueralorenzo.sample.domain.model.Post
import com.squareup.moshi.Json

data class PostEntity(
    @field:Json(name = "userId") val userId: String,
    @field:Json(name = "id") val id: String,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "body") val body: String
)

fun PostEntity.mapToDomain(): Post = Post(userId, id, title, body)

fun List<PostEntity>.mapToDomain(): List<Post> = map { it.mapToDomain() }
