package com.sanogueralorenzo.sample.presentation.model

import com.sanogueralorenzo.sample.domain.model.Comment

data class CommentItem(
    val postId: String,
    val id: String,
    val name: String,
    val email: String,
    val body: String
)

/**
 * Presentation layer should be responsible of mapping the domain model to an
 * appropriate presentation model and the presentation model to a domain model if needed.
 *
 * This is because domain should contain only business logic
 * and shouldn't know at all about presentation or data layers.
 */

fun List<Comment>.mapToPresentation(): List<CommentItem> =
    map { CommentItem(it.postId, it.id, it.name, it.email, it.body) }
