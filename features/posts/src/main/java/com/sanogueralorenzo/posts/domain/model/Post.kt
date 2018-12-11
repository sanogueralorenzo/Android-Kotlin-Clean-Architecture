package com.sanogueralorenzo.posts.domain.model

data class Post(
    val userId: String,
    val id: String,
    val title: String,
    val body: String
)
