package com.sanogueralorenzo.data.model

import com.sanogueralorenzo.domain.model.Comment
import com.squareup.moshi.Json
import javax.inject.Inject

data class CommentEntity(@Json(name = "postId") val postId: String,
                         @Json(name = "id") val id: String,
                         @Json(name = "name") val name: String,
                         @Json(name = "email") val email: String,
                         @Json(name = "body") val body: String)

class CommentMapper @Inject constructor() {

    fun mapToDomain(entity: CommentEntity): Comment = Comment(entity.postId,
            entity.id,
            entity.name,
            entity.email,
            entity.body)

    fun mapToDomain(list: List<CommentEntity>): List<Comment> = list.map { mapToDomain(it) }

    fun mapToEntity(comment: Comment): CommentEntity = CommentEntity(comment.postId,
            comment.id,
            comment.name,
            comment.email,
            comment.body)

    fun mapToEntity(list: List<Comment>): List<CommentEntity> = list.map { mapToEntity(it) }
}
