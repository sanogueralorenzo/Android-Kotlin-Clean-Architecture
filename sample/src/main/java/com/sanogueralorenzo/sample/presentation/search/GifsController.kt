package com.sanogueralorenzo.sample.presentation.search

import android.widget.ImageView
import com.airbnb.epoxy.TypedEpoxyController
import com.sanogueralorenzo.sample.domain.Gif
import com.sanogueralorenzo.views.imageRow

class GifsController(private val itemClick: (Gif) -> Unit) : TypedEpoxyController<GifsState>() {

    override fun buildModels(data: GifsState) {
        data.items.map {
            imageRow {
                id(it.id)
                url(it.thumbnail)
                height(IMAGE_HEIGHT)
                imageScaleType(ImageView.ScaleType.CENTER_CROP)
                clickListener { _ -> itemClick(it) }
                spanSizeOverride { _, _, _ -> 1 }
            }
        }
    }

    private companion object {
        const val IMAGE_HEIGHT = 200
    }
}
