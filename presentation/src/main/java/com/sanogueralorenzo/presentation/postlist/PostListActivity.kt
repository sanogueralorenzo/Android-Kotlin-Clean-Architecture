package com.sanogueralorenzo.presentation.postlist

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.sanogueralorenzo.presentation.*
import com.sanogueralorenzo.presentation.model.PostItem
import com.sanogueralorenzo.presentation.navigation.PostListNavigator
import com.sanogueralorenzo.presentation.navigation.UserDetailsNavigator
import kotlinx.android.synthetic.main.activity_post_list.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class PostListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var postListNavigator: PostListNavigator
    @Inject
    lateinit var userDetailsNavigator: UserDetailsNavigator

    private val avatarClick: (String) -> Unit = { userDetailsNavigator.navigate(this, it) }
    private val itemClick: (PostItem) -> Unit = { postListNavigator.navigate(this, it) }
    private val adapter = PostListAdapter(avatarClick, itemClick)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)
        getAppInjector().inject(this)

        postsRecyclerView.adapter = adapter
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorAccent))

        withViewModel<PostListViewModel>(viewModelFactory) {
            swipeRefreshLayout.setOnRefreshListener { get(refresh = true) }
            observe(posts, ::updateState)
        }
    }

    private fun updateState(resource: Resource<List<PostItem>>?) {
        resource?.let {
            when(it.status) {
                ResourceState.LOADING -> { swipeRefreshLayout.startRefreshing() }
                ResourceState.SUCCESS -> { swipeRefreshLayout.stopRefreshing() }
                ResourceState.ERROR -> { swipeRefreshLayout.stopRefreshing() }
            }
            it.data?.let { adapter.addItems(it) }
            it.message?.let { toast(it) }
        }
    }
}
