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
import com.sanogueralorenzo.views.binding.viewBinding
import com.sanogueralorenzo.views.databinding.FragmentViewPagerBinding
import com.sanogueralorenzo.views.extensions.isGone
import com.sanogueralorenzo.views.extensions.onFragmentBackCallback
import com.sanogueralorenzo.views.extensions.setContainerPadding
import com.sanogueralorenzo.views.extensions.visible

abstract class ViewPagerFragment : Fragment(R.layout.fragment_view_pager) {

    val binding: FragmentViewPagerBinding by viewBinding()

    abstract fun stateAdapter(): FragmentStateAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.removeOverScroll()
        onFragmentBackCallback(
            { binding.viewPager.currentItem = binding.viewPager.currentItem - 1 },
            { binding.viewPager.currentItem != 0 })
        binding.viewPager.adapter = stateAdapter()
    }

    protected inline val appBarLayout: AppBarLayout
        get() = binding.containerAppBarLayout

    protected inline val toolbar: Toolbar
        get() {
            if (binding.containerToolbar.isGone()) binding.containerToolbar.visible()
            return binding.containerToolbar
        }

    protected inline val viewPager: ViewPager2
        get() = binding.viewPager

    protected inline val bottomView: ViewGroup
        get() {
            if (binding.containerBottomLl.childCount == 0) binding.containerBottomLl.setContainerPadding()
            return binding.containerBottomLl
        }
}

fun ViewPager2.removeOverScroll() {
    val child: View = this.getChildAt(0)
    if (child is RecyclerView) {
        child.overScrollMode = View.OVER_SCROLL_NEVER
    }
}
