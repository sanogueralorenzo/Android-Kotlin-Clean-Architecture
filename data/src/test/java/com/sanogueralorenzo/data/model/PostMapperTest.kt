@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.data.model

import com.sanogueralorenzo.data.createPost
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class PostMapperTest {

    private lateinit var mapper: PostMapper

    @Before
    fun setUp() {
        mapper = PostMapper()
    }

    @Test
    fun `map entity to domain`() {
        // given
        val entity = PostEntity("1", "2", "title", "body")

        // when
        val model = mapper.mapToDomain(entity)

        // then
        assertTrue(model.userId == entity.userId)
        assertTrue(model.id == entity.id)
        assertTrue(model.title == entity.title)
        assertTrue(model.body == entity.body)
    }

    @Test
    fun `map domain to entity`() {
        // given
        val post = createPost()

        // when
        val entity = mapper.mapToEntity(post)

        // then
        assertTrue(entity.userId == post.userId)
        assertTrue(entity.id == post.id)
        assertTrue(entity.title == post.title)
        assertTrue(entity.body == post.body)
    }
}
