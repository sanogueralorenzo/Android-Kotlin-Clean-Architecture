@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.sample.datasource.model

import com.sanogueralorenzo.sample.userEntity
import org.junit.Assert.assertTrue
import org.junit.Test

class UserEntityTest {

    @Test
    fun `map entity to domain`() {
        // given

        // when
        val model = userEntity.mapToDomain()

        // then
        assertTrue(model.id == userEntity.id)
        assertTrue(model.name == userEntity.name)
        assertTrue(model.username == userEntity.username)
        assertTrue(model.email == userEntity.email)
    }
}
