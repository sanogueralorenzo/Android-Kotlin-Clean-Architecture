package com.sanogueralorenzo.domain.usecase

import com.sanogueralorenzo.domain.model.Post
import com.sanogueralorenzo.domain.model.User
import com.sanogueralorenzo.domain.repository.PostRepository
import com.sanogueralorenzo.domain.repository.UserRepository
import io.reactivex.Flowable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

/**
 * The standard library provides Pair and Triple.
 * In most cases, though, named data classes are a better design choice, because they make the code more readable by providing meaningful names for properties.
 */
data class CombinedUserPost(val user: User, val post: Post)

class UsersPostsUseCase @Inject constructor(private val userRepository: UserRepository,
                                            private val postRepository: PostRepository,
                                            private val mapper: UserPostMapper) : UseCase<Flowable<List<CombinedUserPost>>> {

    override fun execute(): Flowable<List<CombinedUserPost>> = Flowable.zip(userRepository.getUsers(), postRepository.getPosts(),
            BiFunction { userList, postList -> mapper.map(userList, postList) })

}

class UserPostUseCase @Inject constructor(private val userRepository: UserRepository,
                                          private val postRepository: PostRepository,
                                          private val mapper: UserPostMapper) : UseCase<Flowable<CombinedUserPost>> {

    lateinit var userId: String
    lateinit var postId: String

    override fun execute(): Flowable<CombinedUserPost> = Flowable.zip(userRepository.getUser(userId), postRepository.getPost(postId),
            BiFunction { user, post -> mapper.map(user, post) })

}

/**
 * To obtain the user from a post we need to use the userId from the post to find it in the user list.
 * This is a limitation that comes from the network API and this specific use case requires both posts and users.
 */
class UserPostMapper @Inject constructor() {

    fun map(user: User, post: Post): CombinedUserPost = CombinedUserPost(user, post)

    fun map(userList: List<User>, postList: List<Post>): List<CombinedUserPost> = postList.map { post -> CombinedUserPost(userList.first { post.userId == it.id }, post) }
}
