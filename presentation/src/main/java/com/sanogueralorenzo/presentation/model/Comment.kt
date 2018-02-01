package com.sanogueralorenzo.presentation.model

import com.sanogueralorenzo.domain.model.Comment
import javax.inject.Inject

data class CommentItem(val postId: String, val id: String, val name: String, val email: String, val body: String)

/**
 * Presentation layer should be responsible to map the domain model to an appropriate presentation model.
 *
 * This is because domain should contain only business logic and shouldn't know at all about presentation or data layers.
 */
class CommentItemMapper @Inject constructor() {

    fun map(commentsList: List<Comment>): List<CommentItem> = commentsList.map { CommentItem(it.postId, it.id, it.name, it.email, it.body) }
}
