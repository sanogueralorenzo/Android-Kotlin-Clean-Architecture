@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.data.repository

import com.nhaarman.mockito_kotlin.mock
import com.sanogueralorenzo.data.cache.Cache
import com.sanogueralorenzo.data.createPost
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

    private lateinit var repository: PostRepositoryImpl

    private val mockPostsApi = mock<PostsApi> {}
    private val mockCache = mock<Cache<List<PostEntity>>>()
    private val mapper = PostMapper()

    private val key = "Post List"

    private val postEntityList = listOf(createPostEntity())
    private val postList = listOf(createPost())

    @Before
    fun setUp() {
        repository = PostRepositoryImpl(mockPostsApi, mockCache, mapper)
    }

    @Test
    fun `get posts success`() {
        // given
        _when(mockPostsApi.getPosts()).thenReturn(Single.just(postEntityList))
        _when(mockCache.load(key, emptyList())).thenReturn(Single.just(postEntityList))
        _when(mockCache.save(key, postEntityList)).thenReturn(Completable.complete())

        // when
        val test = repository.getPosts().test()

        // then
        verify(mockPostsApi).getPosts()
        verify(mockCache).load(key, emptyList())
        verify(mockCache).save(key, postEntityList)

        test.assertNoErrors()
        test.assertValueCount(1)
        test.assertComplete()
        test.assertValue(postList)
    }
}
