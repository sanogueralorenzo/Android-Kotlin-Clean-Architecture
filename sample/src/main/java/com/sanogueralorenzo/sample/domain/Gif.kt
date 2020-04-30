package com.sanogueralorenzo.sample.domain

/**
 * Domain object allows mapping from a heavily nested response to a plain object
 */
data class Gif(
    val id: String,
    val url: String,
    val title: String,
    val username: String,
    val thumbnail: String,
    val original: String
)
