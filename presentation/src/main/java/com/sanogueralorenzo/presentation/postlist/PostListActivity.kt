package com.sanogueralorenzo.presentation.postlist

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.sanogueralorenzo.presentation.*
import com.sanogueralorenzo.presentation.model.PostItem
import com.sanogueralorenzo.presentation.navigation.PostDetailsNavigator
import com.sanogueralorenzo.presentation.navigation.UserDetailsNavigator
import kotlinx.android.synthetic.main.activity_post_list.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class PostListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var postDetailsNavigator: PostDetailsNavigator
    @Inject
    lateinit var userDetailsNavigator: UserDetailsNavigator

    private val avatarClick: (String) -> Unit = { userDetailsNavigator.navigate(this, it) }
    private val itemClick: (PostItem) -> Unit = { postDetailsNavigator.navigate(this, it) }
    private val adapter = PostListAdapter(avatarClick, itemClick)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)
        getAppInjector().inject(this)

        postsRecyclerView.adapter = adapter
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorAccent))

        withViewModel<PostListViewModel>(viewModelFactory) {
            swipeRefreshLayout.setOnRefreshListener { get(refresh = true) }
            observe(posts, ::updatePosts)
        }
    }

    private fun updatePosts(data: Data<List<PostItem>>?) {
        data?.let {
            when (it.dataState) {
                DataState.LOADING -> swipeRefreshLayout.startRefreshing()
                DataState.SUCCESS -> swipeRefreshLayout.stopRefreshing()
                DataState.ERROR -> swipeRefreshLayout.stopRefreshing()
            }
            it.data?.let { adapter.addItems(it) }
            it.message?.let { toast(it) }
        }
    }
}
