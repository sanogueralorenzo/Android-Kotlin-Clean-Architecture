package com.sanogueralorenzo.domain.repository

import com.sanogueralorenzo.domain.model.User
import io.reactivex.Single

interface UserRepository {

    fun getRemote(): Single<List<User>>

    fun getRemote(userId: String): Single<User>

    fun getCache(): Single<List<User>>

    fun getCache(userId: String): Single<User>
}
