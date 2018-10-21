package com.sanogueralorenzo.posts.presentation.postdetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.sanogueralorenzo.posts.R
import com.sanogueralorenzo.posts.presentation.POST_ID_KEY
import com.sanogueralorenzo.posts.presentation.USER_ID_KEY
import com.sanogueralorenzo.posts.presentation.loadAvatar
import com.sanogueralorenzo.posts.presentation.model.CommentItem
import com.sanogueralorenzo.posts.presentation.model.PostItem
import com.sanogueralorenzo.presentation.Resource
import com.sanogueralorenzo.presentation.ResourceState
import com.sanogueralorenzo.presentation.gone
import com.sanogueralorenzo.presentation.visible
import kotlinx.android.synthetic.main.activity_post_details.*
import kotlinx.android.synthetic.main.include_user_info.*
import kotlinx.android.synthetic.main.item_list_post.*
import org.koin.android.viewmodel.ext.android.viewModel

class PostDetailsActivity : AppCompatActivity() {

    private val vm: PostDetailsViewModel by viewModel()
    private val adapter = CommentsAdapter()
    private val userId by lazy { intent.getStringExtra(USER_ID_KEY) }
    private val postId by lazy { intent.getStringExtra(POST_ID_KEY) }
    private val snackBar by lazy {
        Snackbar.make(container, getString(R.string.error), Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.retry)) { vm.getComments(postId, refresh = true) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        commentsRecyclerView.isNestedScrollingEnabled = false
        commentsRecyclerView.adapter = adapter

        if (savedInstanceState == null) {
            vm.getPost(UserIdPostId(userId, postId))
            vm.getComments(postId)
        }

        vm.post.observe(this, Observer { updatePost(it) })
        vm.comments.observe(this, Observer { updateComments(it) })
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

    private fun updateComments(resource: Resource<List<CommentItem>>?) {
        resource?.let {
            when (it.state) {
                ResourceState.LOADING -> progressBar.visible()
                ResourceState.SUCCESS -> progressBar.gone()
                ResourceState.ERROR -> progressBar.gone()
            }
            it.data?.let { adapter.addItems(it) }
            it.message?.let { snackBar.show() }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
