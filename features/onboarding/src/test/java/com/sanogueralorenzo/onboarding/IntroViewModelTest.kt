package com.sanogueralorenzo.onboarding

import com.airbnb.mvrx.Success
import com.airbnb.mvrx.test.MvRxTestRule
import com.airbnb.mvrx.withState
import com.sanogueralorenzo.usermanager.UserManager
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

internal class IntroViewModelTest {

    @get:Rule
    val mvrxRule = MvRxTestRule()

    private val mockUserManager: UserManager = mockk()

    private lateinit var viewModel: IntroViewModel

    private fun createViewModel(state: IntroState = IntroState()): IntroViewModel =
        IntroViewModel(state, mockUserManager)

    @Test
    fun `on button click user manager new user is set to false and screen state completes`() {
        every { mockUserManager.newUser = false } just runs

        viewModel = createViewModel()
        viewModel.onButtonClick()

        verify { mockUserManager.newUser = false }
        withState(viewModel) { assert(it.complete == Success(Unit)) }
    }
}
