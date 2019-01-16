package com.sanogueralorenzo.sample.data.datasource

import com.sanogueralorenzo.sample.domain.model.User
import io.reactivex.Single

interface UserCacheDataSource {

    fun get(): Single<List<User>>

    fun set(item: User): Single<User>

    fun get(userId: String): Single<User>

    fun set(list: List<User>): Single<List<User>>
}

interface UserRemoteDataSource {

    fun get(): Single<List<User>>

    fun get(userId: String): Single<User>
}
