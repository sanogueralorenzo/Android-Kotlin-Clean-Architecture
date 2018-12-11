package com.sanogueralorenzo.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class LiveDataExtensionsTest {

    private lateinit var liveData: MutableLiveData<Resource<Int>>

    private val data = 1
    private val errorMessage = "Error"

    @Rule
    @JvmField
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        liveData = MutableLiveData()
    }

    @Test
    fun `loading to success`() {
        // given
        liveData.setLoading()

        // when
        liveData.setSuccess(data)

        // then
        assertEquals(Resource(ResourceState.SUCCESS, data, null), liveData.value)
    }

    @Test
    fun `loading to success to loading`() {
        // given
        liveData.setLoading()
        liveData.setSuccess(data)

        // when
        liveData.setLoading()

        // then
        assertEquals(Resource(ResourceState.LOADING, data, null), liveData.value)
    }

    @Test
    fun `loading to success to loading to error`() {
        // given
        liveData.setLoading()
        liveData.setSuccess(data)
        liveData.setLoading()

        // when
        liveData.setError(errorMessage)

        // then
        assertEquals(Resource(ResourceState.ERROR, data, errorMessage), liveData.value)
    }

    @Test
    fun `loading to error`() {
        // given
        liveData.setLoading()

        // when
        liveData.setError(errorMessage)

        // then
        assertEquals(Resource(ResourceState.ERROR, null, errorMessage), liveData.value)
    }

    @Test
    fun `loading to error to loading to success`() {
        // given
        liveData.setLoading()
        liveData.setError(errorMessage)
        liveData.setLoading()

        // when
        liveData.setSuccess(data)

        // then
        assertEquals(Resource(ResourceState.SUCCESS, data, null), liveData.value)
    }
}
