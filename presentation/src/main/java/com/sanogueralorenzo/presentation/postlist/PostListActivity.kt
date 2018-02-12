package com.sanogueralorenzo.presentation.postlist

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.sanogueralorenzo.presentation.R
import com.sanogueralorenzo.presentation.getAppInjector
import com.sanogueralorenzo.presentation.model.PostItem
import com.sanogueralorenzo.presentation.navigation.PostListNavigator
import com.sanogueralorenzo.presentation.navigation.UserDetailsNavigator
import kotlinx.android.synthetic.main.activity_post_list.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class PostListActivity : AppCompatActivity(), PostListView {

    override fun loading(show: Boolean) {
        swipeRefreshLayout.isRefreshing = show
    }

    override fun add(list: List<PostItem>) {
        adapter.addItems(list)
    }

    override fun error() {
        toast(getString(R.string.error))
    }

    @Inject
    lateinit var presenter: PostListPresenter
    @Inject
    lateinit var postListNavigator: PostListNavigator
    @Inject
    lateinit var userDetailsNavigator: UserDetailsNavigator

    private val avatarClick: (String) -> Unit = {
        userDetailsNavigator.navigate(this, it)
    }

    private val itemClick: (PostItem) -> Unit = {
        postListNavigator.navigate(this, it)
    }

    private val adapter = PostListAdapter(avatarClick, itemClick)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)
        getAppInjector().inject(this)
        postsRecyclerView.adapter = adapter
        swipeRefreshLayout.setOnRefreshListener { presenter.get(true) }
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorAccent))
        presenter.attachView(this)
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }
}
