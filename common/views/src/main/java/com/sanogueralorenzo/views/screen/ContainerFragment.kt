package com.sanogueralorenzo.views.screen

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyRecyclerView
import com.airbnb.mvrx.MavericksView
import com.google.android.material.appbar.AppBarLayout
import com.sanogueralorenzo.views.ErrorDisplay
import com.sanogueralorenzo.views.R
import com.sanogueralorenzo.views.binding.viewBinding
import com.sanogueralorenzo.views.databinding.FragmentContainerBinding
import com.sanogueralorenzo.views.extensions.enable
import com.sanogueralorenzo.views.extensions.enableStateRestoration
import com.sanogueralorenzo.views.extensions.isGone
import com.sanogueralorenzo.views.extensions.setContainerPadding
import com.sanogueralorenzo.views.extensions.visible

abstract class ContainerFragment : Fragment(R.layout.fragment_container), MavericksView {

    val binding: FragmentContainerBinding by viewBinding()

    protected val controller by lazy { controller() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerRecyclerView.setDelayMsWhenRemovingAdapterOnDetach(0)
        binding.containerRecyclerView.setController(controller)
        binding.containerRecyclerView.enableStateRestoration()
    }

    abstract fun controller(): EpoxyController

    protected inline val toolbar: Toolbar
        get() {
            if (binding.containerToolbar.isGone()) binding.containerToolbar.visible()
            return binding.containerToolbar
        }

    protected inline val appBarLayout: AppBarLayout
        get() = binding.containerAppBarLayout

    protected inline val swipeRefreshLayout: SwipeRefreshLayout
        get() {
            if (binding.containerSrl.isEnabled.not()) binding.containerSrl.enable()
            return binding.containerSrl
        }

    protected inline val recyclerView: EpoxyRecyclerView
        get() = binding.containerRecyclerView

    protected inline val bottomView: ViewGroup
        get() {
            if (binding.containerBottomLl.childCount == 0) binding.containerBottomLl.setContainerPadding()
            return binding.containerBottomLl
        }

    protected fun showError(throwable: Throwable, retry: (() -> Unit)? = null) {
        // TODO Implement navigational errors when they arrive
        // Probably have an error modules for generic throwables?
        // Examples: App Update, Logout (Token expired), Authentication required
        // if (throwable.isNavigationalError()) return <- screen will not display error and just navigate
        ErrorDisplay.create(binding.container, throwable, retry)
            .apply { this.anchorView = binding.containerBottomLl.getChildAt(0) ?: binding.containerBottomLl }
            .show()
    }

    override fun invalidate() {
        recyclerView.requestModelBuild()
    }

    override fun onStart() {
        super.onStart()
        // Required for Mavericks 2.0+ for static screens with no ViewModel
        postInvalidate()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        controller.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        controller.cancelPendingModelBuild()
        super.onDestroyView()
    }
}
