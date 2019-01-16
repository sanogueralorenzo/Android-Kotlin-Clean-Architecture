@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.sample.presentation.model

import com.sanogueralorenzo.sample.domain.usecase.CombinedUserPost
import com.sanogueralorenzo.sample.post
import com.sanogueralorenzo.sample.user
import org.junit.Assert.assertTrue
import org.junit.Test

class PostItemTest {

    @Test
    fun `map domain to presentation`() {
        // given
        val combinedUserPost = CombinedUserPost(user, post)

        // when
        val postItem = combinedUserPost.mapToPresentation()

        // then
        assertTrue(postItem.postId == post.id)
        assertTrue(postItem.userId == user.id)
        assertTrue(postItem.title == post.title)
        assertTrue(postItem.body == post.body)
        assertTrue(postItem.name == user.name)
        assertTrue(postItem.username == user.username)
        assertTrue(postItem.email == user.email)
    }
}
