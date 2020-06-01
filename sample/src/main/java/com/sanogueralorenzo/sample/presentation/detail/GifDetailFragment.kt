package com.sanogueralorenzo.sample.presentation.detail

import android.content.Intent
import android.os.Bundle
import com.sanogueralorenzo.views.extensions.sendIntent
import com.sanogueralorenzo.views.imageRow
import com.sanogueralorenzo.views.screen.ContainerFragment
import com.sanogueralorenzo.views.screen.simpleController

class GifDetailFragment : ContainerFragment() {

    private val url by lazy { arguments!!.getString("url")!! }

    override fun controller() = simpleController {
        imageRow {
            id("image")
            url(url)
            clickListener { _ -> startActivity(Intent.createChooser(sendIntent(url), null)) }
        }
    }

    companion object {
        /**
         * Alternatives if it was a more complex screen:
         * 1. Just pass the id and fetch from memory cache
         * 2. Have a shared parent ViewModel and just look up the data
         */
        fun newInstance(url: String): GifDetailFragment {
            val args = Bundle()
            args.putString("url", url)
            val fragment = GifDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
