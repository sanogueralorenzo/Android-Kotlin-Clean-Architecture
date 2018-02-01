@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.data.repository

import com.nhaarman.mockito_kotlin.mock
import com.sanogueralorenzo.data.cache.Cache
import com.sanogueralorenzo.data.createPostEntity
import com.sanogueralorenzo.data.model.PostEntity
import com.sanogueralorenzo.data.model.PostMapper
import com.sanogueralorenzo.data.remote.PostsApi
import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.Mockito.`when` as _when

class PostRepositoryImplTest {

    private lateinit var postRepository: PostRepositoryImpl

    private val mockPostsApi = mock<PostsApi> {}
    private val mockCache = mock<Cache<List<PostEntity>>>()
    private val mapper = PostMapper()

    private val key = "Post List"

    @Before
    fun setUp() {
        postRepository = PostRepositoryImpl(mockPostsApi, mockCache, mapper)
    }

    @Test
    fun `repository get remote success`() {
        // given
        val entity = createPostEntity()
        val list = listOf(entity)
        _when(mockPostsApi.getPosts()).thenReturn(Single.just(list))
        _when(mockCache.save(key, list)).thenReturn(Completable.complete())

        // when
        val test = postRepository.getRemote().test()

        // then
        verify(mockPostsApi).getPosts()
        verify(mockCache).save(key, list)

        test.assertNoErrors()
        test.assertValueCount(1)
        test.assertComplete()
        test.assertValue(listOf(mapper.mapToDomain(entity)))
    }

    @Test
    fun `repository get remote fail`() {
        // given
        val throwable = Throwable()
        _when(mockPostsApi.getPosts()).thenReturn(Single.error(throwable))

        // when
        val test = postRepository.getRemote().test()

        // then
        verify(mockPostsApi).getPosts()
        verify(mockCache, never()).save(anyString(), anyList())

        test.assertError(throwable)
        test.assertValueCount(0)
        test.assertNotComplete()
        test.assertNoValues()
    }
}
