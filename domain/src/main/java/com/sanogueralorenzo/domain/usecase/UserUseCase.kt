package com.sanogueralorenzo.domain.usecase

import com.sanogueralorenzo.domain.model.User
import com.sanogueralorenzo.domain.repository.UserRepository
import io.reactivex.Single
import javax.inject.Inject

class UserUseCase @Inject constructor(private val repository: UserRepository) : UseCase<Single<User>> {

    lateinit var userId: String

    override fun execute(): Single<User> = getCache()

    fun getCache(): Single<User> = repository.getCache(userId)
}
