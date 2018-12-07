package com.sanogueralorenzo.navigation

import android.content.Intent

const val USER_ID_KEY = "USER_ID_KEY"
const val POST_ID_KEY = "POST_ID_KEY"

object PostsNavigation : DynamicFeature {

    override val dynamicModule = "posts"
    override val dynamicIntent: Intent?
        get() = loadIntentOrNull("com.sanogueralorenzo.posts.presentation.postlist.PostListActivity")

    fun postDetails(userId: String, postId: String): Intent? =
        loadIntentOrNull("com.sanogueralorenzo.posts.presentation.postdetails.PostDetailsActivity")
            ?.apply {
                putExtra(USER_ID_KEY, userId)
                putExtra(POST_ID_KEY, postId)
            }
}
