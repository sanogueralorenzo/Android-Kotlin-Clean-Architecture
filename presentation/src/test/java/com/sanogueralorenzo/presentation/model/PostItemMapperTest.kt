@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.presentation.model

import com.sanogueralorenzo.domain.usecase.CombinedUserPost
import com.sanogueralorenzo.presentation.createPost
import com.sanogueralorenzo.presentation.createUser
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class PostItemMapperTest {

    private lateinit var mapper: PostItemMapper

    @Before
    fun setUp() {
        mapper = PostItemMapper()
    }

    @Test
    fun `map domain to presentation`() {
        // given
        val user = createUser()
        val post = createPost()
        val combinedUserPost = CombinedUserPost(user, post)
        // when
        val postItem = mapper.map(listOf(combinedUserPost))[0]

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
