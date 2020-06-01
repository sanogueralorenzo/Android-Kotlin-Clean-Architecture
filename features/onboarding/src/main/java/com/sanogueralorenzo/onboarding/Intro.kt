package com.sanogueralorenzo.onboarding

import android.os.Bundle
import android.view.Gravity
import android.view.View
import com.airbnb.mvrx.Async
import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.Uninitialized
import com.airbnb.mvrx.ViewModelContext
import com.airbnb.mvrx.fragmentViewModel
import com.sanogueralorenzo.navigation.features.HomeNavigation
import com.sanogueralorenzo.usermanager.UserManager
import com.sanogueralorenzo.views.PrimaryButton
import com.sanogueralorenzo.views.TextRow
import com.sanogueralorenzo.views.extensions.addHorizontalItemDecorator
import com.sanogueralorenzo.views.extensions.startEndPadding
import com.sanogueralorenzo.views.extensions.topBottomPadding
import com.sanogueralorenzo.views.imageRow
import com.sanogueralorenzo.views.imageTextRow
import com.sanogueralorenzo.views.screen.ContainerFragment
import com.sanogueralorenzo.views.screen.simpleController
import com.sanogueralorenzo.views.spaceRow
import com.sanogueralorenzo.views.textRow
import io.reactivex.Completable

/**
 * @see com.sanogueralorenzo.navigation.features.OnboardingNavigation.intro
 */
@Suppress("Unused")
class IntroFragment : ContainerFragment() {
    private val viewModel: IntroViewModel by fragmentViewModel(IntroViewModel::class)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        TextRow(context!!).apply {
            gravity = Gravity.CENTER
            text = getString(R.string.privacy_terms)
            startEndPadding()
            topBottomPadding(16)
            setStyle(TextRow.TextStyle.CAPTION)
        }.let { bottomView.addView(it) }
        PrimaryButton.create(context!!, getString(R.string.onboarding_intro_button)) {
            viewModel.onButtonClick()
        }.let { bottomView.addView(it) }
        recyclerView.addHorizontalItemDecorator(64)

        viewModel.asyncSubscribe(IntroState::complete, uniqueOnly(), onSuccess = {
            startActivity(HomeNavigation.homeIntent())
            activity?.finish()
        })
    }

    override fun controller() = simpleController {
        spaceRow {
            id("top_space")
            size(60)
        }
        imageRow {
            id("logo")
            drawable(R.drawable.ic_logo)
            height(80)
        }
        spaceRow {
            id("image_bottom_space")
            size(16)
        }
        textRow {
            id("title")
            body(R.string.onboarding_welcome_title)
            style(TextRow.TextStyle.HEADLINE)
            bodyGravity(Gravity.CENTER)
        }
        spaceRow {
            id("title_bottom_space")
            size(60)
        }
        imageTextRow {
            id("journey")
            image(R.drawable.ic_section_journal)
            title(getString(R.string.intro_journal_title))
            subtitle(getString(R.string.intro_journal_subtitle))
        }
        spaceRow {
            id("journey_bottom_space")
            size(32)
        }
        imageTextRow {
            id("habits")
            image(R.drawable.ic_section_habits)
            title(getString(R.string.intro_habits_title))
            subtitle(getString(R.string.intro_habits_subtitle))
        }
        spaceRow {
            id("habits_bottom_space")
            size(32)
        }
        imageTextRow {
            id("zoneout")
            image(R.drawable.ic_section_zoneout)
            title(getString(R.string.intro_zoneout_title))
            subtitle(getString(R.string.intro_zoneout_subtitle))
        }
    }
}

class IntroViewModel(
    state: IntroState,
    private val userManager: UserManager = UserManager()
) : BaseMvRxViewModel<IntroState>(state) {

    fun onButtonClick() {
        withState {
            userManager.newUser = false
            Completable.complete().execute { copy(complete = it) }
        }
    }

    companion object : MvRxViewModelFactory<IntroViewModel, IntroState> {

        override fun create(
            viewModelContext: ViewModelContext,
            state: IntroState
        ): IntroViewModel = IntroViewModel(state)
    }
}

data class IntroState(
    val complete: Async<Unit> = Uninitialized
) : MvRxState
