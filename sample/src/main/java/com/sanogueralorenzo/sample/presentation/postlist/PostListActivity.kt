package com.sanogueralorenzo.sample.presentation.postlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.sanogueralorenzo.navigation.features.SampleNavigation
import com.sanogueralorenzo.sample.R
import com.sanogueralorenzo.sample.injectFeature
import com.sanogueralorenzo.sample.presentation.model.PostItem
import com.sanogueralorenzo.presentation.Resource
import com.sanogueralorenzo.presentation.ResourceState
import com.sanogueralorenzo.presentation.startRefreshing
import com.sanogueralorenzo.presentation.stopRefreshing
import kotlinx.android.synthetic.main.activity_post_list.*
import org.koin.androidx.viewmodel.ext.viewModel

class PostListActivity : AppCompatActivity() {

    private val vm: PostListViewModel by viewModel()

    private val itemClick: (PostItem) -> Unit =
        { startActivity(SampleNavigation.postDetails(userId = it.userId, postId = it.postId)) }
    private val adapter = PostListAdapter(itemClick)
    private val snackBar by lazy {
        Snackbar.make(swipeRefreshLayout, getString(R.string.error), Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.retry)) { vm.get(refresh = true) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)

        injectFeature()

        if (savedInstanceState == null) {
            vm.get(refresh = false)
        }

        postsRecyclerView.adapter = adapter

        vm.posts.observe(this, Observer { updatePosts(it) })
        swipeRefreshLayout.setOnRefreshListener { vm.get(refresh = true) }
    }

    private fun updatePosts(resource: Resource<List<PostItem>>?) {
        resource?.let {
            when (it.state) {
                ResourceState.LOADING -> swipeRefreshLayout.startRefreshing()
                ResourceState.SUCCESS -> swipeRefreshLayout.stopRefreshing()
                ResourceState.ERROR -> swipeRefreshLayout.stopRefreshing()
            }
            it.data?.let { adapter.submitList(it) }
            it.message?.let { snackBar.show() }
        }
    }
}
