package com.sanogueralorenzo.posts

import com.sanogueralorenzo.posts.domain.model.Comment
import com.sanogueralorenzo.posts.domain.model.Post
import com.sanogueralorenzo.posts.domain.model.User
import com.sanogueralorenzo.posts.domain.usecase.CombinedUserPost

val user = User("userId", "name", "username", "email")
val post = Post("userId", "postId", "title", "body")
val comment = Comment("postId", "commentId", "name", "email", "body")

val combinedUserPost = CombinedUserPost(user, post)
