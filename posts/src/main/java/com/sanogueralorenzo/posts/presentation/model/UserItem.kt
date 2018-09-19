package com.sanogueralorenzo.posts.presentation.model

import com.sanogueralorenzo.posts.domain.model.User

const val USER_ID_KEY = "USER_ID_KEY"

data class UserItem(
    val id: String,
    val name: String,
    val username: String,
    val email: String
)

class UserItemMapper {

    fun mapToPresentation(user: User): UserItem = UserItem(
        id = user.id,
        name = user.name,
        username = user.username,
        email = user.email
    )

    fun mapToPresentation(list: List<User>): List<UserItem> = list.map { mapToPresentation(it) }
}
