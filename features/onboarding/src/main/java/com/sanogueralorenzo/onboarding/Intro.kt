package com.sanogueralorenzo.onboarding

import android.os.Bundle
import android.view.Gravity
import android.view.View
import com.sanogueralorenzo.navigation.features.HomeNavigation
import com.sanogueralorenzo.usermanager.UserManager
import com.sanogueralorenzo.views.MiniButton
import com.sanogueralorenzo.views.TextRow
import com.sanogueralorenzo.views.extensions.addHorizontalItemDecorator
import com.sanogueralorenzo.views.extensions.bottomPadding
import com.sanogueralorenzo.views.extensions.setTextWithLinks
import com.sanogueralorenzo.views.extensions.topBottomPadding
import com.sanogueralorenzo.views.extensions.urlIntent
import com.sanogueralorenzo.views.imageRow
import com.sanogueralorenzo.views.imageTextRow
import com.sanogueralorenzo.views.screen.ContainerFragment
import com.sanogueralorenzo.views.screen.simpleController
import com.sanogueralorenzo.views.spaceRow
import com.sanogueralorenzo.views.textRow
import com.sanogueralorenzo.resources.R as G

/** @see com.sanogueralorenzo.navigation.features.OnboardingNavigation.intro */
@Suppress("Unused")
class IntroFragment : ContainerFragment() {

    private val terms: Pair<String, () -> Unit> by lazy {
        Pair(getString(R.string.terms),
            { startActivity(urlIntent(getString(R.string.terms_url))) })
    }
    private val privacy: Pair<String, () -> Unit> by lazy {
        Pair(getString(R.string.privacy),
            { startActivity(urlIntent(getString(R.string.privacy_url))) })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        TextRow(requireContext()).apply {
            gravity = Gravity.CENTER
            topBottomPadding(16)
            setStyle(TextRow.TextStyle.CAPTION)
            setTextWithLinks(getString(R.string.privacy_terms), terms, privacy)
        }.let { bottomView.addView(it) }

        MiniButton.create(requireContext(), getString(R.string.intro_button)) {
            UserManager().newUser = false
            startActivity(HomeNavigation.home())
            requireActivity().finish()
        }.let {
            it.bottomPadding(8)
            bottomView.addView(it)
        }

        recyclerView.addHorizontalItemDecorator(64)
    }

    override fun controller() = simpleController {
        spaceRow {
            id("top_space")
            size(60)
        }
        imageRow {
            id("logo")
            drawable(G.drawable.ic_logo)
            height(80)
        }
        spaceRow {
            id("image_bottom_space")
            size(16)
        }
        textRow {
            id("title")
            body(R.string.intro_title)
            style(TextRow.TextStyle.HEADLINE)
            bodyGravity(Gravity.CENTER)
        }
        spaceRow {
            id("title_bottom_space")
            size(60)
        }
        imageTextRow {
            id("journey")
            image(G.drawable.ic_section_journal)
            title(getString(R.string.intro_journal_title))
            subtitle(getString(R.string.intro_journal_subtitle))
        }
        spaceRow {
            id("journey_bottom_space")
            size(32)
        }
        imageTextRow {
            id("habits")
            image(G.drawable.ic_section_habits)
            title(getString(R.string.intro_habits_title))
            subtitle(getString(R.string.intro_habits_subtitle))
        }
        spaceRow {
            id("habits_bottom_space")
            size(32)
        }
        imageTextRow {
            id("zoneout")
            image(G.drawable.ic_section_zoneout)
            title(getString(R.string.intro_zoneout_title))
            subtitle(getString(R.string.intro_zoneout_subtitle))
        }
    }
}
