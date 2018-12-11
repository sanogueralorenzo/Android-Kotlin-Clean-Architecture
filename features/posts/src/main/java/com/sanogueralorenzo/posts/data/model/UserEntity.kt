package com.sanogueralorenzo.posts.data.model

import com.sanogueralorenzo.posts.domain.model.User
import com.squareup.moshi.Json

data class UserEntity(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "username") val username: String,
    @field:Json(name = "email") val email: String
)

fun UserEntity.mapToDomain(): User = User(id, name, username, email)

fun List<UserEntity>.mapToDomain(): List<User> = map { it.mapToDomain() }
