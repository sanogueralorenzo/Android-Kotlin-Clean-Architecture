package com.sanogueralorenzo.posts.domain.usecase

import com.sanogueralorenzo.posts.domain.model.User
import com.sanogueralorenzo.posts.domain.repository.UserRepository
import io.reactivex.Single

class UserUseCase constructor(private val repository: UserRepository) {

    fun get(userId: String, refresh: Boolean): Single<User> = repository.get(userId, refresh)
}
