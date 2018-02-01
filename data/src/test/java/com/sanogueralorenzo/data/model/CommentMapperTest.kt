@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.data.model

import com.sanogueralorenzo.data.createComment
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class CommentMapperTest {

    private lateinit var mapper: CommentMapper

    @Before
    fun setUp() {
        mapper = CommentMapper()
    }

    @Test
    fun `map entity to domain`() {
        // given
        val entity = CommentEntity("1", "2", "name", "email", "body")

        // when
        val model = mapper.mapToDomain(entity)

        // then
        assertTrue(model.postId == entity.postId)
        assertTrue(model.id == entity.id)
        assertTrue(model.name == entity.name)
        assertTrue(model.email == entity.email)
        assertTrue(model.body == entity.body)
    }

    @Test
    fun `map domain to entity`() {
        // given
        val comment = createComment()

        // when
        val entity = mapper.mapToEntity(comment)

        // then
        assertTrue(entity.postId == comment.postId)
        assertTrue(entity.id == comment.id)
        assertTrue(entity.name == comment.name)
        assertTrue(entity.email == comment.email)
        assertTrue(entity.body == comment.body)
    }
}
