package com.sanogueralorenzo.sample.presentation.model

import com.sanogueralorenzo.sample.domain.usecase.CombinedUserPost

data class PostItem(
    val postId: String,
    val userId: String,
    val title: String,
    val body: String,
    val name: String,
    val username: String,
    val email: String
)

/**
 * Presentation layer should be responsible of mapping the domain model to an
 * appropriate presentation model and the presentation model to a domain model if needed.
 *
 * This is because domain should contain only business logic
 * and shouldn't know at all about presentation or data layers.
 */
fun CombinedUserPost.mapToPresentation(): PostItem = PostItem(
    post.id,
    user.id,
    post.title,
    post.body,
    user.name,
    user.username,
    user.email
)

fun List<CombinedUserPost>.mapToPresentation(): List<PostItem> = map { it.mapToPresentation() }
