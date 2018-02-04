package com.sanogueralorenzo.domain.repository

import com.sanogueralorenzo.domain.model.User
import io.reactivex.Flowable

interface UserRepository {

    fun getUsers(): Flowable<List<User>>

    fun getUser(userId: String): Flowable<User>
}
