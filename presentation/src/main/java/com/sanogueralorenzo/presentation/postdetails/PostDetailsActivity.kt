package com.sanogueralorenzo.presentation.postdetails

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.sanogueralorenzo.presentation.Data
import com.sanogueralorenzo.presentation.DataState
import com.sanogueralorenzo.presentation.R
import com.sanogueralorenzo.presentation.getAppInjector
import com.sanogueralorenzo.presentation.gone
import com.sanogueralorenzo.presentation.loadAvatar
import com.sanogueralorenzo.presentation.model.CommentItem
import com.sanogueralorenzo.presentation.model.POST_ID_KEY
import com.sanogueralorenzo.presentation.model.PostItem
import com.sanogueralorenzo.presentation.model.USER_ID_KEY
import com.sanogueralorenzo.presentation.navigation.UserDetailsNavigator
import com.sanogueralorenzo.presentation.observe
import com.sanogueralorenzo.presentation.visible
import com.sanogueralorenzo.presentation.withViewModel
import kotlinx.android.synthetic.main.activity_post_details.*
import kotlinx.android.synthetic.main.include_user_info.*
import kotlinx.android.synthetic.main.item_list_post.*
import javax.inject.Inject

class PostDetailsActivity : AppCompatActivity() {

    @Inject
    lateinit var adapter: CommentsAdapter
    @Inject
    lateinit var userDetailsNavigator: UserDetailsNavigator
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: PostDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)
        getAppInjector().inject(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        commentsRecyclerView.isNestedScrollingEnabled = false
        commentsRecyclerView.adapter = adapter
        userAvatar.setOnClickListener {
            userDetailsNavigator.navigate(this, intent.getStringExtra(USER_ID_KEY))
        }

        withViewModel<PostDetailsViewModel>(viewModelFactory) {
            userIdPostId =
                UserIdPostId(intent.getStringExtra(USER_ID_KEY), intent.getStringExtra(POST_ID_KEY))
            viewModel = this
            observe(post, ::updatePost)
            observe(comments, ::updateComments)
        }
    }

    private fun updatePost(postItem: PostItem?) {
        postItem?.let {
            userAvatar.loadAvatar(it.email)
            userUsername.text = "@${it.username}"
            userName.text = it.name
            postTitle.text = it.title.capitalize()
            postBody.maxLines = Int.MAX_VALUE
            postBody.text = it.body.capitalize()
        }
    }

    private fun updateComments(data: Data<List<CommentItem>>?) {
        data?.let {
            when (it.dataState) {
                DataState.LOADING -> progressBar.visible()
                DataState.SUCCESS -> progressBar.gone()
                DataState.ERROR -> progressBar.gone()
            }
            it.data?.let { adapter.addItems(it) }
            it.message?.let {
                Snackbar.make(container, R.string.error, Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.retry), { viewModel.getComments(true) })
                    .setDuration(Snackbar.LENGTH_INDEFINITE)
                    .show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
