package com.sanogueralorenzo.posts.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.sanogueralorenzo.posts.presentation.model.PostItem
import com.sanogueralorenzo.posts.presentation.postdetails.PostDetailsActivity

const val USER_ID_KEY = "USER_ID_KEY"
const val POST_ID_KEY = "POST_ID_KEY"

fun AppCompatActivity.startPostDetails(postItem: PostItem) {
    val intent = Intent(this, PostDetailsActivity::class.java)
    intent.putExtra(USER_ID_KEY, postItem.userId)
    intent.putExtra(POST_ID_KEY, postItem.postId)
    startActivity(intent)
}
