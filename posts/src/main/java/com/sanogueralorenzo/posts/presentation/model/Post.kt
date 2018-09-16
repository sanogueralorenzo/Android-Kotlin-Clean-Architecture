package com.sanogueralorenzo.posts.presentation.model

import com.sanogueralorenzo.posts.domain.usecase.CombinedUserPost
import java.io.Serializable

const val POST_ID_KEY = "POST_ID_KEY"

data class PostItem(
    val postId: String,
    val userId: String,
    val title: String,
    val body: String,
    val name: String,
    val username: String,
    val email: String
) : Serializable

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

fun mapToPresentation(combinedUserPostList: List<CombinedUserPost>): List<PostItem> =
    combinedUserPostList.map { it.mapToPresentation() }
