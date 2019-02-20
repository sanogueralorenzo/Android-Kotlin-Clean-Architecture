package com.sanogueralorenzo.sample.datasource.cache

import com.sanogueralorenzo.cache.ReactiveCache
import com.sanogueralorenzo.sample.data.datasource.UserCacheDataSource
import com.sanogueralorenzo.sample.domain.model.User
import io.reactivex.Single

class UserCacheDataSourceImpl constructor(
    private val cache: ReactiveCache<List<User>>
) : UserCacheDataSource {

    val key = "User List"

    override fun get(): Single<List<User>> =
        cache.load(key)

    override fun set(list: List<User>): Single<List<User>> =
        cache.save(key, list)

    override fun get(userId: String): Single<User> =
        cache.load(key)
            .map { it.first { it.id == userId } }

    override fun set(item: User): Single<User> =
        cache.load(key)
            .map { it.filter { it.id != item.id }.plus(item) }
            .flatMap { set(it) }
            .map { item }
}
