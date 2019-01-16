package com.sanogueralorenzo.sample

import com.sanogueralorenzo.sample.datasource.model.CommentEntity
import com.sanogueralorenzo.sample.datasource.model.PostEntity
import com.sanogueralorenzo.sample.datasource.model.UserEntity
import com.sanogueralorenzo.sample.domain.model.Comment
import com.sanogueralorenzo.sample.domain.model.Post
import com.sanogueralorenzo.sample.domain.model.User
import com.sanogueralorenzo.sample.domain.usecase.CombinedUserPost

val user = User("userId", "name", "username", "email")
val post = Post("userId", "postId", "title", "body")
val comment = Comment("postId", "commentId", "name", "email", "body")

val combinedUserPost = CombinedUserPost(user, post)

val userEntity = UserEntity("userId", "name", "username", "email")
val postEntity = PostEntity("userId", "postId", "title", "body")
val commentEntity = CommentEntity("postId", "commentId", "name", "email", "body")
