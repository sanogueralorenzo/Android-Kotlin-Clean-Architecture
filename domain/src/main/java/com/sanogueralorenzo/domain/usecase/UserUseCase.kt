package com.sanogueralorenzo.domain.usecase

import com.sanogueralorenzo.domain.model.User
import com.sanogueralorenzo.domain.repository.UserRepository
import io.reactivex.Single
import javax.inject.Inject

class UserUseCase @Inject constructor(private val repository: UserRepository) {

    fun get(userId: String, refresh: Boolean): Single<User> = repository.get(userId, refresh)
}
