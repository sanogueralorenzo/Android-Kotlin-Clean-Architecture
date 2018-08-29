package com.sanogueralorenzo.data.repository

import com.sanogueralorenzo.data.cache.Cache
import com.sanogueralorenzo.data.model.UserEntity
import com.sanogueralorenzo.data.model.UserMapper
import com.sanogueralorenzo.data.remote.UsersApi
import com.sanogueralorenzo.domain.model.User
import com.sanogueralorenzo.domain.repository.UserRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val api: UsersApi,
    private val cache: Cache<List<UserEntity>>,
    private val mapper: UserMapper
) : UserRepository {
    override val key = "User List"

    override fun get(refresh: Boolean): Single<List<User>> = when (refresh) {
        true -> api.getUsers().flatMap { set(it) }.map { mapper.mapToDomain(it) }
        false -> cache.load(key).map { mapper.mapToDomain(it) }.onErrorResumeNext { get(true) }
    }

    override fun get(userId: String, refresh: Boolean): Single<User> = when (refresh) {
        true -> api.getUser(userId).flatMap { set(it) }.map { mapper.mapToDomain(it) }
        false -> cache.load(key).map { it.first { it.id == userId } }.map { mapper.mapToDomain(it) }.onErrorResumeNext { get(userId, true) }
    }

    private fun set(list: List<UserEntity>) = cache.save(key, list)

    private fun set(entity: UserEntity) = cache.load(key).map { it.filter { it.id != entity.id }.plus(entity) }.flatMap { set(it) }.map { entity }
}
