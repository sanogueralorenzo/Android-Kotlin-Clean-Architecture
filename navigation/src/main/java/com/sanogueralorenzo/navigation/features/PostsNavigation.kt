package com.sanogueralorenzo.navigation.features

import android.content.Intent
import com.sanogueralorenzo.navigation.loadIntentOrNull

object PostsNavigation : DynamicFeature<Intent> {

    const val USER_ID_KEY = "USER_ID_KEY"
    const val POST_ID_KEY = "POST_ID_KEY"

    private const val POST_LIST_ENTRY_POINT = "com.sanogueralorenzo.posts.presentation.postlist.PostListActivity"
    private const val POST_DETAILS_FEATURE_NAVIGATION = "com.sanogueralorenzo.posts.presentation.postdetails.PostDetailsActivity"

    override val dynamicStart: Intent?
        get() = POST_LIST_ENTRY_POINT.loadIntentOrNull()


    fun postDetails(userId: String, postId: String): Intent? =
        POST_DETAILS_FEATURE_NAVIGATION.loadIntentOrNull()
            ?.apply {
                putExtra(USER_ID_KEY, userId)
                putExtra(POST_ID_KEY, postId)
            }
}
