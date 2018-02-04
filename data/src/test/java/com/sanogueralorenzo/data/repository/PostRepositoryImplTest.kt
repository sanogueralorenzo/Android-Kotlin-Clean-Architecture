@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.data.repository

import com.nhaarman.mockito_kotlin.mock
import com.sanogueralorenzo.data.cache.Cache
import com.sanogueralorenzo.data.createPostEntity
import com.sanogueralorenzo.data.model.PostEntity
import com.sanogueralorenzo.data.model.PostMapper
import com.sanogueralorenzo.data.remote.PostsApi
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.Mockito.`when` as _when

class PostRepositoryImplTest {

    private lateinit var repository: PostRepositoryImpl

    private val mockApi = mock<PostsApi> {}
    private val mockCache = mock<Cache<List<PostEntity>>>()
    private val mapper = PostMapper()

    private val key = "Post List"

    private val cacheList = listOf(createPostEntity().copy(title = "cache"))
    private val remoteList = listOf(createPostEntity().copy(title = "remote"))

    @Before
    fun setUp() {
        repository = PostRepositoryImpl(mockApi, mockCache, mapper)
    }

    @Test
    fun `get posts success`() {
        // given
        _when(mockCache.load(key, emptyList())).thenReturn(Single.just(cacheList))
        _when(mockApi.getPosts()).thenReturn(Single.just(remoteList))
        _when(mockCache.save(key, remoteList)).thenReturn(Single.just(remoteList))

        // when
        val test = repository.getPosts().test()

        // then
        verify(mockCache).load(key, emptyList())
        verify(mockApi).getPosts()
        verify(mockCache).save(key, remoteList)

        test.assertNoErrors()
        test.assertValueCount(2)
        test.assertComplete()
        test.assertValues(mapper.mapToDomain(cacheList), mapper.mapToDomain(remoteList))
    }

    @Test
    fun `get posts fail`() {
        // given
        val throwable = Throwable()
        _when(mockCache.load(key, emptyList())).thenReturn(Single.just(emptyList()))
        _when(mockApi.getPosts()).thenReturn(Single.error(throwable))

        // when
        val test = repository.getPosts().test()

        // then
        verify(mockApi).getPosts()
        verify(mockCache).load(key, emptyList())
        verify(mockCache, never()).save(anyString(), anyList())

        test.assertError(throwable)
        test.assertValueCount(1)
        test.assertNotComplete()
        test.assertValue(emptyList())
    }
}
