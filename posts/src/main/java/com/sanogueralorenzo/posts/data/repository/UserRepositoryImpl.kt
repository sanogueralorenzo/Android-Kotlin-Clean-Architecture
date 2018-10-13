package com.sanogueralorenzo.posts.data.repository

import com.sanogueralorenzo.cache.Cache
import com.sanogueralorenzo.posts.data.model.UserEntity
import com.sanogueralorenzo.posts.data.model.mapToDomain
import com.sanogueralorenzo.posts.data.remote.UsersApi
import com.sanogueralorenzo.posts.domain.model.User
import com.sanogueralorenzo.posts.domain.repository.UserRepository
import io.reactivex.Single

class UserRepositoryImpl constructor(
    private val userApi: UsersApi,
    private val cache: Cache<List<UserEntity>>
) : UserRepository {

    val key = "User List"

    override fun get(refresh: Boolean): Single<List<User>> =
        when (refresh) {
            true -> userApi.getUsers()
                .flatMap { set(it) }
                .map { it.mapToDomain() }
            false -> cache.load(key)
                .map { it.mapToDomain() }
                .onErrorResumeNext { get(true) }
        }

    override fun get(userId: String, refresh: Boolean): Single<User> =
        when (refresh) {
            true -> userApi.getUser(userId)
                .flatMap { set(it) }
                .map { it.mapToDomain() }
            false -> cache.load(key)
                .map { it.first { it.id == userId } }
                .map { it.mapToDomain() }
                .onErrorResumeNext {
                    get(userId, true)
                }
        }

    private fun set(list: List<UserEntity>) = cache.save(key, list)

    private fun set(entity: UserEntity) =
        cache.load(key)
            .map { it.filter { it.id != entity.id }.plus(entity) }
            .flatMap { set(it) }.map { entity }
}
