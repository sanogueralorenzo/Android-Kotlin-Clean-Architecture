package com.sanogueralorenzo.posts.presentation

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.sanogueralorenzo.posts.presentation.model.PostItem
import com.sanogueralorenzo.posts.presentation.postdetails.PostDetailsActivity
import com.sanogueralorenzo.posts.presentation.postlist.PostListActivity

const val USER_ID_KEY = "USER_ID_KEY"
const val POST_ID_KEY = "POST_ID_KEY"

fun AppCompatActivity.startPostDetails(postItem: PostItem) {
    val intent = Intent(this, PostDetailsActivity::class.java)
    intent.putExtra(USER_ID_KEY, postItem.userId)
    intent.putExtra(POST_ID_KEY, postItem.postId)
    startActivity(intent)
}

fun AppCompatActivity.startUserDetails(userId: String) {
    val intent = Intent(this, PostListActivity::class.java)
    intent.putExtra(USER_ID_KEY, userId)
    startActivity(intent)
}
