package com.sanogueralorenzo.sample.data.repository

import com.sanogueralorenzo.sample.data.datasource.UserCacheDataSource
import com.sanogueralorenzo.sample.data.datasource.UserRemoteDataSource
import com.sanogueralorenzo.sample.domain.model.User
import com.sanogueralorenzo.sample.domain.repository.UserRepository
import io.reactivex.Single

class UserRepositoryImpl constructor(
    private val cacheDataSource: UserCacheDataSource,
    private val remoteDataSource: UserRemoteDataSource
) : UserRepository {

    override fun get(refresh: Boolean): Single<List<User>> =
        when (refresh) {
            true -> remoteDataSource.get()
                .flatMap { cacheDataSource.set(it) }
            false -> cacheDataSource.get()
                .onErrorResumeNext { get(true) }
        }

    override fun get(userId: String, refresh: Boolean): Single<User> =
        when (refresh) {
            true -> remoteDataSource.get(userId)
                .flatMap { cacheDataSource.set(it) }
            false -> cacheDataSource.get(userId)
                .onErrorResumeNext { get(userId, true) }
        }
}
