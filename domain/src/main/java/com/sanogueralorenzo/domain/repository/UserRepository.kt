package com.sanogueralorenzo.domain.repository

import com.sanogueralorenzo.domain.model.User
import io.reactivex.Single

interface UserRepository {

    val key: String

    fun get(refresh: Boolean): Single<List<User>>

    fun get(userId: String, refresh: Boolean): Single<User>
}
