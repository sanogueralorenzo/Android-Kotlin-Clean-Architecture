package com.sanogueralorenzo.sample.presentation.detail

import android.os.Bundle
import android.view.View
import com.airbnb.epoxy.TypedEpoxyController
import com.sanogueralorenzo.views.fullImage
import com.sanogueralorenzo.views.screen.ContainerFragment

class GifDetailFragment : ContainerFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controller.setData(arguments!!.getString("url")!!)
    }

    override val controller: GifDetailController = GifDetailController()

    override fun invalidate() {
        // Bad practice, should there be an MvRxContainerFragment & ContainerFragment?
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

class GifDetailController : TypedEpoxyController<String>() {

    override fun buildModels(data: String) {
        fullImage {
            id("image")
            url(data)
        }
    }
}
