package com.sanogueralorenzo.sample.domain.repository

import com.sanogueralorenzo.sample.domain.model.User
import io.reactivex.Single

interface UserRepository {

    fun get(refresh: Boolean): Single<List<User>>

    fun get(userId: String, refresh: Boolean): Single<User>
}
