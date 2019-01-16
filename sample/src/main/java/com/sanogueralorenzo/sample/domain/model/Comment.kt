package com.sanogueralorenzo.sample.domain.model

data class Comment(
    val postId: String,
    val id: String,
    val name: String,
    val email: String,
    val body: String
)
