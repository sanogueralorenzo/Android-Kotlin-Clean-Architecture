package com.sanogueralorenzo.views.screen

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.AppBarLayout
import com.sanogueralorenzo.views.R
import com.sanogueralorenzo.views.extensions.isGone
import com.sanogueralorenzo.views.extensions.onFragmentBackCallback
import com.sanogueralorenzo.views.extensions.setContainerPadding
import com.sanogueralorenzo.views.extensions.visible
import kotlinx.android.synthetic.main.fragment_view_pager.*

abstract class ViewPagerFragment : Fragment(R.layout.fragment_view_pager) {

    abstract fun stateAdapter(): FragmentStateAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view_pager.removeOverScroll()
        onFragmentBackCallback(
            { view_pager.currentItem = view_pager.currentItem - 1 },
            { view_pager.currentItem != 0 })
        view_pager.adapter = stateAdapter()
    }

    protected inline val appBarLayout: AppBarLayout
        get() = containerAppBarLayout

    protected inline val toolbar: Toolbar
        get() {
            if (containerToolbar.isGone()) containerToolbar.visible()
            return containerToolbar
        }

    protected inline val viewPager: ViewPager2
        get() = view_pager

    protected inline val bottomView: ViewGroup
        get() {
            if (containerBottomLL.childCount == 0) containerBottomLL.setContainerPadding()
            return containerBottomLL
        }
}

fun ViewPager2.removeOverScroll() {
    val child: View = this.getChildAt(0)
    if (child is RecyclerView) {
        child.overScrollMode = View.OVER_SCROLL_NEVER
    }
}
