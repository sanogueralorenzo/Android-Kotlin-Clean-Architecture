@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.presentation.userdetails

import com.nhaarman.mockito_kotlin.mock
import com.sanogueralorenzo.domain.usecase.UserUseCase
import com.sanogueralorenzo.presentation.RxSchedulersOverrideRule
import com.sanogueralorenzo.presentation.createUser
import com.sanogueralorenzo.presentation.model.AddressItemMapper
import com.sanogueralorenzo.presentation.model.CompanyItemMapper
import com.sanogueralorenzo.presentation.model.LatLngMapper
import com.sanogueralorenzo.presentation.model.UserItemMapper
import io.reactivex.Flowable
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when` as _when

class UserDetailsPresenterTest {

    private lateinit var presenter: UserDetailsPresenter

    private val mockUseCase = mock<UserUseCase> {}
    private val mockView = mock<UserDetailsView> {}
    private val mapper = UserItemMapper(addressItemMapper = AddressItemMapper(LatLngMapper()), companyItemMapper = CompanyItemMapper())

    private val userId = "1"
    private val user = createUser()

    //Rule to avoid having schedulers all around https://github.com/ReactiveX/RxAndroid/issues/238
    @Rule
    @JvmField
    val testRule: RxSchedulersOverrideRule = RxSchedulersOverrideRule()

    @Before
    fun setUp() {
        presenter = UserDetailsPresenter(mockUseCase, mapper)
    }

    @Test
    fun `getting user details success`() {
        // given
        _when(mockUseCase.get(userId, false)).thenReturn(Single.just(user))

        // when
        presenter.attachView(mockView)
        presenter.get(userId, false)

        // then
        verify(mockView).showUser(mapper.mapToPresentation(user))
    }
}
