package com.sanogueralorenzo.presentation.postdetails

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.sanogueralorenzo.presentation.*
import com.sanogueralorenzo.presentation.model.CommentItem
import com.sanogueralorenzo.presentation.model.POST_ID_KEY
import com.sanogueralorenzo.presentation.model.PostItem
import com.sanogueralorenzo.presentation.model.USER_ID_KEY
import com.sanogueralorenzo.presentation.navigation.UserDetailsNavigator
import kotlinx.android.synthetic.main.activity_post_details.*
import kotlinx.android.synthetic.main.include_user_info.*
import kotlinx.android.synthetic.main.item_list_post.*
import javax.inject.Inject

data class UserIdPostId(val userId: String, val postId: String)

class PostDetailsActivity : AppCompatActivity(), PostDetailsView {

    override fun showPost(item: PostItem) {
        userAvatar.loadAvatar(item.email)
        userUsername.text = "@${item.username}"
        userName.text = item.name
        postTitle.text = item.title.capitalize()
        postBody.maxLines = Int.MAX_VALUE
        postBody.text = item.body.capitalize()
    }

    override fun loading(show: Boolean) = when (show) {
        true -> progressBar.visible()
        false -> progressBar.gone()
    }

    override fun add(list: List<CommentItem>) {
        adapter.addItems(list)
    }

    override fun error() {
        Snackbar.make(container, R.string.error, Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.retry), { presenter.getComments(postId, true) })
                .setDuration(Snackbar.LENGTH_INDEFINITE)
                .show()
    }

    @Inject
    lateinit var presenter: PostDetailsPresenter
    @Inject
    lateinit var userDetailsNavigator: UserDetailsNavigator
    @Inject
    lateinit var adapter: CommentsAdapter

    lateinit var userId: String
    lateinit var postId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)
        getAppInjector().inject(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        commentsRecyclerView.isNestedScrollingEnabled = false
        commentsRecyclerView.adapter = adapter
        userId = intent.getStringExtra(USER_ID_KEY)
        postId = intent.getStringExtra(POST_ID_KEY)
        userAvatar.setOnClickListener { userDetailsNavigator.navigate(this, intent.getStringExtra(USER_ID_KEY)) }
        presenter.attachView(this)
        presenter.getPost(userId, postId)
        presenter.getComments(postId)
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
