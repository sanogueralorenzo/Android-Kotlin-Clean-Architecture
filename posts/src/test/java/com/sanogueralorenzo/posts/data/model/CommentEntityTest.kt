@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.posts.data.model

import com.sanogueralorenzo.posts.commentEntity
import org.junit.Assert.assertTrue
import org.junit.Test

class CommentEntityTest {

    @Test
    fun `map entity to domain`() {
        // given

        // when
        val model = commentEntity.mapToDomain()

        // then
        assertTrue(model.postId == commentEntity.postId)
        assertTrue(model.id == commentEntity.id)
        assertTrue(model.name == commentEntity.name)
        assertTrue(model.email == commentEntity.email)
        assertTrue(model.body == commentEntity.body)
    }
}
