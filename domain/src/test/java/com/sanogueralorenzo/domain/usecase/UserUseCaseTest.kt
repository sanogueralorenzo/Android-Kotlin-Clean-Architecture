@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.domain.usecase

import com.nhaarman.mockito_kotlin.mock
import com.sanogueralorenzo.domain.createUser
import com.sanogueralorenzo.domain.repository.UserRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when` as _when

class UserUseCaseTest {

    private lateinit var usecase: UserUseCase

    private val mockRepository = mock<UserRepository> {}

    private val userId = "1"
    private val user = createUser()

    @Before
    fun setUp() {
        usecase = UserUseCase(mockRepository)
    }

    @Test
    fun `repository get success`() {
        // given
        _when(mockRepository.get(userId, false)).thenReturn(Single.just(user))

        // when
        val test = usecase.get(userId, false).test()

        // then
        verify(mockRepository).get(userId, false)

        test.assertNoErrors()
        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue(user)
    }
}
