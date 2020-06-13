package com.sanogueralorenzo.sample.presentation.detail

import android.content.Intent
import androidx.core.os.bundleOf
import com.sanogueralorenzo.navigation.features.GifsNavigation
import com.sanogueralorenzo.views.extensions.sendIntent
import com.sanogueralorenzo.views.imageRow
import com.sanogueralorenzo.views.screen.ContainerFragment
import com.sanogueralorenzo.views.screen.simpleController

class GifDetailFragment : ContainerFragment() {

    private val url by lazy { arguments!!.getString(URL_KEY)!! }

    override fun controller() = simpleController {
        imageRow {
            id("image")
            url(url)
            clickListener { _ -> startActivity(Intent.createChooser(sendIntent(url), null)) }
        }
    }

    companion object {
        private const val URL_KEY = "url"

        fun newInstance(url: String) = GifsNavigation
            .fragment(GifDetailFragment::class, bundleOf(URL_KEY to url))
    }
}
