package com.sanogueralorenzo.profile

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

internal class NameViewModelTest {

    @get:Rule
    val mvrxRule = MvRxTestRule()

    private val mockUserManager: UserManager = mockk()

    private lateinit var viewModel: NameViewModel

    private fun createViewModel(state: NameState = NameState()): NameViewModel =
        NameViewModel(state, mockUserManager)

    @Test
    fun `on name changed with non blank string removes error state and updates name`() {
        viewModel = createViewModel(NameState(error = R.string.onboarding_name_error))
        viewModel.onNameChanged("Mario")

        withState(viewModel) { assert(it.error == null) }
    }

    @Test
    fun `on name changed with blank string keeps previous error and updates name`() {
        viewModel = createViewModel(NameState(error = R.string.onboarding_name_error))
        viewModel.onNameChanged("   ")

        withState(viewModel) { assert(it.error == R.string.onboarding_name_error) }
    }

    @Test
    fun `on button click with name sets user manager information and calls complete`() {
        val name = "Mario"
        val state = NameState(name = name)
        every { mockUserManager.name = name } just runs

        viewModel = createViewModel(state)
        viewModel.onButtonClick()

        verify { mockUserManager.name = name }
        withState(viewModel) { assert(it.complete == Success(Unit)) }
    }

    @Test
    fun `on button click with empty name sets error and does not complete`() {
        viewModel = createViewModel()
        viewModel.onButtonClick()

        withState(viewModel) { assert(it.error == R.string.onboarding_name_error) }
    }
}