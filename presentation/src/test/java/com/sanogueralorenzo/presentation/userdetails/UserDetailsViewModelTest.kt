@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.presentation.userdetails

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.mock
import com.sanogueralorenzo.domain.usecase.UserUseCase
import com.sanogueralorenzo.presentation.RxSchedulersOverrideRule
import com.sanogueralorenzo.presentation.createUser
import com.sanogueralorenzo.presentation.model.AddressItemMapper
import com.sanogueralorenzo.presentation.model.CompanyItemMapper
import com.sanogueralorenzo.presentation.model.LatLngMapper
import com.sanogueralorenzo.presentation.model.UserItemMapper
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.`when` as _when

class UserDetailsViewModelTest {

    private lateinit var viewModel: UserDetailsViewModel

    private val mockUseCase = mock<UserUseCase> {}
    private val mapper = UserItemMapper(addressItemMapper = AddressItemMapper(LatLngMapper()), companyItemMapper = CompanyItemMapper())

    private val userId = "1"
    private val user = createUser()

    @Rule
    @JvmField
    val rxSchedulersOverrideRule: RxSchedulersOverrideRule = RxSchedulersOverrideRule()

    @Rule
    @JvmField
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = UserDetailsViewModel(mockUseCase, mapper)
    }

    @Test
    fun `get user details succeeds`() {
        // given
        _when(mockUseCase.get(userId, false)).thenReturn(Single.just(user))

        // when
        viewModel.userId = userId

        // then
        assertEquals(mapper.mapToPresentation(user), viewModel.user.value)
    }
}
