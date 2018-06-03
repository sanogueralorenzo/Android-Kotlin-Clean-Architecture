package com.sanogueralorenzo.presentation.navigation

import android.app.Activity
import android.content.Intent
import com.sanogueralorenzo.presentation.model.POST_ID_KEY
import com.sanogueralorenzo.presentation.model.PostItem
import com.sanogueralorenzo.presentation.model.USER_ID_KEY
import com.sanogueralorenzo.presentation.postdetails.PostDetailsActivity
import com.sanogueralorenzo.presentation.userdetails.UserDetailsActivity
import javax.inject.Inject

class PostDetailsNavigator @Inject constructor() {

    fun navigate(activity: Activity, postItem: PostItem) {
        val intent = Intent(activity, PostDetailsActivity::class.java)
        intent.putExtra(USER_ID_KEY, postItem.userId)
        intent.putExtra(POST_ID_KEY, postItem.postId)
        activity.startActivity(intent)
    }
}

class UserDetailsNavigator @Inject constructor() {

    fun navigate(activity: Activity, userId: String) {
        val intent = Intent(activity, UserDetailsActivity::class.java)
        intent.putExtra(USER_ID_KEY, userId)
        activity.startActivity(intent)
    }
}