package com.sanogueralorenzo.posts.presentation.postlist

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.sanogueralorenzo.posts.R
import com.sanogueralorenzo.posts.presentation.model.PostItem
import com.sanogueralorenzo.posts.presentation.startPostDetails
import com.sanogueralorenzo.presentation.Resource
import com.sanogueralorenzo.presentation.ResourceState
import com.sanogueralorenzo.presentation.startRefreshing
import com.sanogueralorenzo.presentation.stopRefreshing
import kotlinx.android.synthetic.main.activity_post_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class PostListActivity : AppCompatActivity() {

    private val vm: PostListViewModel by viewModel()

    private val itemClick: (PostItem) -> Unit = { startPostDetails(it) }
    private val adapter = PostListAdapter(itemClick)
    private val snackBar by lazy {
        Snackbar.make(swipeRefreshLayout, getString(R.string.error), Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.retry)) { vm.get(refresh = true) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)

        if (savedInstanceState == null) {
            vm.get(refresh = false)
        }

        postsRecyclerView.adapter = adapter
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorAccent))

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
            it.data?.let { adapter.addItems(it) }
            it.message?.let { snackBar.show() }
        }
    }
}
