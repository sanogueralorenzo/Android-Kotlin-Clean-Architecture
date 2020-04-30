package com.sanogueralorenzo.views.extensions

import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sanogueralorenzo.views.R

fun RecyclerView.replaceItemDecorator(itemDecorator: RecyclerView.ItemDecoration) {
    for (i in 0 until itemDecorationCount) removeItemDecorationAt(i)
    addItemDecoration(itemDecorator)
}

fun RecyclerView.addHorizontalItemDecorator(@DimenRes horizontalDp: Int = R.dimen.margin_regular) =
    addItemDecoration(HorizontalItemDecorator(horizontalDp))

fun RecyclerView.addVerticalItemDecorator(@DimenRes verticalDp: Int = R.dimen.margin_regular) =
    addItemDecoration(VerticalItemDecorator(verticalDp))

fun RecyclerView.setInfiniteScrolling(layoutManager: GridLayoutManager, action: () -> Unit) {
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val visibleItemCount: Int = layoutManager.childCount
            val totalItemCount: Int = layoutManager.itemCount
            val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()

            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                action()
            }
        }
    })
}

private class HorizontalItemDecorator(@DimenRes private val horizontalDp: Int) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.right = view.context.resources.getDimension(horizontalDp).toInt()
        outRect.left = view.context.resources.getDimension(horizontalDp).toInt()
    }
}

private class VerticalItemDecorator(@DimenRes private val verticalDp: Int) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.top = view.context.resources.getDimension(verticalDp).toInt()
        outRect.bottom = view.context.resources.getDimension(verticalDp).toInt()
    }
}
