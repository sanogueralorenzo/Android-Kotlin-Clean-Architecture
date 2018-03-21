package com.sanogueralorenzo.presentation.postdetails

import android.arch.lifecycle.ViewModelProvider
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

class PostDetailsActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var userDetailsNavigator: UserDetailsNavigator
    @Inject
    lateinit var adapter: CommentsAdapter

    private lateinit var postDetailsViewModel: PostDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)
        getAppInjector().inject(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        commentsRecyclerView.isNestedScrollingEnabled = false
        commentsRecyclerView.adapter = adapter
        userAvatar.setOnClickListener { userDetailsNavigator.navigate(this, intent.getStringExtra(USER_ID_KEY)) }

        withViewModel<PostDetailsViewModel>(viewModelFactory) {
            userIdPostId = UserIdPostId(intent.getStringExtra(USER_ID_KEY), intent.getStringExtra(POST_ID_KEY))
            observe(state, ::updateState)
            observe(post, ::updatePost)
            observe(comments, ::updateComments)
            postDetailsViewModel = this
        }
    }

    private fun updateState(stateData: StateData?) = when (stateData!!) {
        is StateData.Loading -> progressBar.visible()
        is StateData.Success -> progressBar.gone()
        is StateData.Error -> handleErrorState()
    }

    private fun updatePost(item: PostItem?) {
        userAvatar.loadAvatar(item!!.email)
        userUsername.text = "@${item.username}"
        userName.text = item.name
        postTitle.text = item.title.capitalize()
        postBody.maxLines = Int.MAX_VALUE
        postBody.text = item.body.capitalize()
    }

    private fun updateComments(list: List<CommentItem>?) = adapter.addItems(list!!)

    private fun handleErrorState(){
        progressBar.gone()
        errorMessage()
    }

    private fun errorMessage() = Snackbar.make(container, R.string.error, Snackbar.LENGTH_LONG)
            .setAction(getString(R.string.retry), { postDetailsViewModel.getComments(true) })
            .setDuration(Snackbar.LENGTH_INDEFINITE)
            .show()

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
