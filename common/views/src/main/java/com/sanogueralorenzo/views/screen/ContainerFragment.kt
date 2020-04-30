package com.sanogueralorenzo.views.screen

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyRecyclerView
import com.airbnb.mvrx.BaseMvRxFragment
import com.google.android.material.appbar.AppBarLayout
import com.sanogueralorenzo.views.ErrorDisplay
import com.sanogueralorenzo.views.R
import com.sanogueralorenzo.views.extensions.isGone
import com.sanogueralorenzo.views.extensions.setContainerPadding
import com.sanogueralorenzo.views.extensions.setInitialRefresh
import com.sanogueralorenzo.views.extensions.show
import com.sanogueralorenzo.views.extensions.visible
import kotlinx.android.synthetic.main.fragment_container.*

abstract class ContainerFragment : BaseMvRxFragment(R.layout.fragment_container) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        containerRecyclerView.setDelayMsWhenRemovingAdapterOnDetach(0)
        containerRecyclerView.setController(controller)
    }

    abstract val controller: EpoxyController

    protected val toolbar: Toolbar
        get() {
            if (containerToolbar.isGone()) containerToolbar.visible()
            return containerToolbar
        }

    protected val appBarLayout: AppBarLayout
        get() = containerAppBarLayout

    protected val swipeRefreshLayout: SwipeRefreshLayout
        get() {
            if (containerSRL.isEnabled.not()) containerSRL.setInitialRefresh()
            return containerSRL
        }

    protected inline val topView: ViewGroup
        get() {
            if (containerTopLL.childCount == 0) containerTopLL.setContainerPadding()
            return containerTopLL
        }

    protected inline val recyclerView: EpoxyRecyclerView
        get() = containerRecyclerView

    protected inline val bottomView: ViewGroup
        get() {
            if (containerBottomLL.childCount == 0) containerBottomLL.setContainerPadding()
            return containerBottomLL
        }

    protected fun showLoading(loading: Boolean) = containerLoading.show(loading)

    protected fun showError(throwable: Throwable, retry: (() -> Unit)? = null) {
        // TODO Implement navigational errors when they arrive
        // Probably have an error modules for generic throwables?
        // Examples: App Update, Logout (Token expired), Authentication required
        // if (throwable.isNavigationalError()) return <- screen will not display error and just navigate
        ErrorDisplay.create(container, throwable, retry)
            .apply { this.anchorView = containerBottomLL.getChildAt(0) ?: containerBottomLL }
            .show()
    }
}
