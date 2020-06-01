package com.sanogueralorenzo.usermanager

import com.sanogueralorenzo.cache.Cache
import com.sanogueralorenzo.usermanager.UserManager.Companion.NAME
import com.sanogueralorenzo.usermanager.UserManager.Companion.NEW_USER
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.Before
import org.junit.Test

internal class UserManagerTest {

    private lateinit var userManager: UserManager
    private val mockCache: Cache = mockk()

    @Before
    fun setUp() {
        userManager = UserManager(mockCache)
    }

    @Test
    fun getNewUser() {
        val expectedResult = true
        every { mockCache.get<Boolean>(NEW_USER) } returns expectedResult
        assert(userManager.newUser == expectedResult)
    }

    @Test
    fun setNewUser() {
        val value = true
        every { mockCache.set(NEW_USER, value) } just runs
        userManager.newUser = value
        verify { mockCache.set(NEW_USER, value) }
    }

    @Test
    fun getName() {
        val expectedResult = "Mario"
        every { mockCache.get<String>(NAME) } returns expectedResult
        assert(userManager.name == expectedResult)
    }

    @Test
    fun setName() {
        val value = "Mario"
        every { mockCache.set(NAME, value) } just runs
        userManager.name = value
        verify { mockCache.set(NAME, value) }
    }
}