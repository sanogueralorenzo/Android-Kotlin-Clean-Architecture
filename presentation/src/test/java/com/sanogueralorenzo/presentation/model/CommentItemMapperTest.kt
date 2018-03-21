@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.presentation.model

import com.sanogueralorenzo.presentation.createComment
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class CommentItemMapperTest {

    private lateinit var mapper: CommentItemMapper

    @Before
    fun setUp() {
        mapper = CommentItemMapper()
    }

    @Test
    fun `map domain to presentation`() {
        // given
        val comment = createComment()

        // when
        val commentItem = mapper.mapToPresentation(listOf(comment))[0]

        // then
        assertTrue(commentItem.postId == comment.postId)
        assertTrue(commentItem.id == comment.id)
        assertTrue(commentItem.name == comment.name)
        assertTrue(commentItem.email == comment.email)
        assertTrue(commentItem.body == comment.body)
    }
}
