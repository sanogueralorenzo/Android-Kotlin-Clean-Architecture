package com.sanogueralorenzo.domain.usecase

import com.sanogueralorenzo.domain.model.User
import com.sanogueralorenzo.domain.repository.UserRepository
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class UserUseCase @Inject constructor(private val repository: UserRepository) : UseCase<Flowable<User>> {

    lateinit var userId: String

    override fun execute(): Flowable<User> = repository.getUser(userId)
}
