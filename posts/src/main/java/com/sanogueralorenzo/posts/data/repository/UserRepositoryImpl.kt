package com.sanogueralorenzo.posts.data.repository

import com.sanogueralorenzo.cache.Cache
import com.sanogueralorenzo.posts.data.model.UserEntity
import com.sanogueralorenzo.posts.data.remote.UsersApi
import com.sanogueralorenzo.posts.domain.model.User
import com.sanogueralorenzo.posts.domain.repository.UserRepository
import io.reactivex.Single

class UserRepositoryImpl constructor(
    private val api: UsersApi,
    private val cache: Cache<List<UserEntity>>
) : UserRepository {

    override val key = "User List"

    override fun get(refresh: Boolean): Single<List<User>> = Single.just(listOf(User("1", "1", "1", "1")))
//        when (refresh) {
//            true -> api.getUsers()
//                .flatMap { set(it) }
//                .map { mapToDomain(it) }
//            false -> cache.load(key)
//                .map { mapToDomain(it) }
//                .onErrorResumeNext { get(true) }
//        }

    override fun get(userId: String, refresh: Boolean): Single<User> = Single.just(User("1", "1", "1", "1"))
//        when (refresh) {
//            true -> api.getUser(userId)
//                .flatMap { set(it) }
//                .map { it.mapToDomain() }
//            false -> cache.load(key)
//                .map { it.first { it.id == userId } }
//                .map { it.mapToDomain() }
//                .onErrorResumeNext {
//                    get(userId, true)
//                }
//        }

    private fun set(list: List<UserEntity>) = cache.save(key, list)

    private fun set(entity: UserEntity) =
        cache.load(key)
            .map { it.filter { it.id != entity.id }.plus(entity) }
            .flatMap { set(it) }.map { entity }
}
