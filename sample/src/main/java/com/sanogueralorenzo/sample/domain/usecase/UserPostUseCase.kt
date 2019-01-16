package com.sanogueralorenzo.sample.domain.usecase

import com.sanogueralorenzo.sample.domain.model.Post
import com.sanogueralorenzo.sample.domain.model.User
import com.sanogueralorenzo.sample.domain.repository.PostRepository
import com.sanogueralorenzo.sample.domain.repository.UserRepository
import io.reactivex.Single
import io.reactivex.functions.BiFunction

/**
 * The standard library provides Pair and Triple.
 * In most cases, though, named data classes are a better design choice.
 * This is because they make the code more readable by providing meaningful names for properties.
 */
data class CombinedUserPost(val user: User, val post: Post)

class UsersPostsUseCase constructor(
    private val userRepository: UserRepository,
    private val postRepository: PostRepository
) {

    fun get(refresh: Boolean): Single<List<CombinedUserPost>> =
        Single.zip(userRepository.get(refresh), postRepository.get(refresh),
            BiFunction { userList, postList -> map(userList, postList) })
}

class UserPostUseCase constructor(
    private val userRepository: UserRepository,
    private val postRepository: PostRepository
) {

    fun get(userId: String, postId: String, refresh: Boolean): Single<CombinedUserPost> =
        Single.zip(userRepository.get(userId, refresh), postRepository.get(postId, refresh),
            BiFunction { user, post -> map(user, post) })
}

/**
 * To obtain the user from a post we need to use the userId from the post to find it in the user list.
 * This is a limitation that comes from the network API and this specific use case requires both sample and users.
 */
fun map(user: User, post: Post): CombinedUserPost = CombinedUserPost(user, post)

fun map(userList: List<User>, postList: List<Post>): List<CombinedUserPost> =
    postList.map { post -> CombinedUserPost(userList.first { post.userId == it.id }, post) }
