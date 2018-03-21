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
            observe(state, ::updateState)
            observe(posts, ::updatePosts)
            swipeRefreshLayout.setOnRefreshListener { get(true) }
        }
    }

    private fun updateState(stateData: StateData?) = when (stateData!!) {
        is StateData.Loading -> swipeRefreshLayout.startRefreshing()
        is StateData.Success -> swipeRefreshLayout.stopRefreshing()
        is StateData.Error -> errorMessage(stateData.throwable!!)
    }

    private fun updatePosts(list: List<PostItem>?) = adapter.addItems(list!!)

    private fun errorMessage(throwable: Throwable) {
        toast(throwable.message.toString())
    }
}
