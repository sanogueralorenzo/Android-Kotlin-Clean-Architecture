package com.sanogueralorenzo.posts.datasource.remote

import com.sanogueralorenzo.posts.data.datasource.UserRemoteDataSource
import com.sanogueralorenzo.posts.datasource.model.mapToDomain
import com.sanogueralorenzo.posts.domain.model.User
import io.reactivex.Single

class UserRemoteDataSourceImpl constructor(
    private val api: UsersApi
) : UserRemoteDataSource {

    override fun get(): Single<List<User>> =
        api.getUsers()
            .map { it.mapToDomain() }

    override fun get(userId: String): Single<User> =
        api.getUser(userId)
            .map { it.mapToDomain() }
}
