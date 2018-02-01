package com.sanogueralorenzo.presentation.model

import com.sanogueralorenzo.domain.usecase.CombinedUserPost
import java.io.Serializable
import javax.inject.Inject

data class PostItem(val postId: String, val userId: String, val title: String, val body: String, val name: String, val username: String, val email: String) : Serializable

/**
 * Presentation layer should be responsible to map the domain model to an appropriate presentation model.
 *
 * This is because domain should contain only business logic and shouldn't know at all about presentation or data layers.
 */
class PostItemMapper @Inject constructor() {

    fun map(combinedUserPostList: List<CombinedUserPost>): List<PostItem> = combinedUserPostList.map { PostItem(it.post.id, it.user.id, it.post.title, it.post.body, it.user.name, it.user.username, it.user.email) }
}
