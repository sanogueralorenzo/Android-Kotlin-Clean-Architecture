package com.sanogueralorenzo.views.extensions

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.enableStateRestoration() {
    adapter!!.stateRestorationPolicy =
        RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
}

fun RecyclerView.removeAllItemDecorators() {
    for (i in 0 until itemDecorationCount) removeItemDecorationAt(0)
}

fun RecyclerView.addHorizontalItemDecorator(horizontalDp: Int = 16) =
    addItemDecoration(HorizontalItemDecorator(horizontalDp))

fun RecyclerView.addVerticalItemDecorator(verticalDp: Int = 8) =
    addItemDecoration(VerticalItemDecorator(verticalDp))

fun RecyclerView.setInfiniteScrolling(layoutManager: LinearLayoutManager, action: () -> Unit) {
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

private class HorizontalItemDecorator(private val horizontalDp: Int) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.right = view.toDp(horizontalDp)
        outRect.left = view.toDp(horizontalDp)
    }
}

private class VerticalItemDecorator(private val verticalDp: Int) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.top = view.toDp(verticalDp)
        outRect.bottom = view.toDp(verticalDp)
    }
}
