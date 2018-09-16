package com.sanogueralorenzo.posts.data.model

import com.sanogueralorenzo.posts.domain.model.Comment
import com.squareup.moshi.Json

data class CommentEntity(
    @Json(name = "postId") val postId: String,
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "email") val email: String,
    @Json(name = "body") val body: String
)

fun CommentEntity.mapToDomain(): Comment = Comment(postId, id, name, email, body)

fun mapToDomain(list: List<CommentEntity>): List<Comment> = list.map { it.mapToDomain() }
