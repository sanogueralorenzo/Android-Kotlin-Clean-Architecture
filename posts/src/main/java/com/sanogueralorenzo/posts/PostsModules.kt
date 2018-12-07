package com.sanogueralorenzo.posts

import com.sanogueralorenzo.cache.Cache
import com.sanogueralorenzo.network.createNetworkClient
import com.sanogueralorenzo.posts.data.model.CommentEntity
import com.sanogueralorenzo.posts.data.model.PostEntity
import com.sanogueralorenzo.posts.data.model.UserEntity
import com.sanogueralorenzo.posts.data.remote.CommentsApi
import com.sanogueralorenzo.posts.data.remote.PostsApi
import com.sanogueralorenzo.posts.data.remote.UsersApi
import com.sanogueralorenzo.posts.data.repository.CommentRepositoryImpl
import com.sanogueralorenzo.posts.data.repository.PostRepositoryImpl
import com.sanogueralorenzo.posts.data.repository.UserRepositoryImpl
import com.sanogueralorenzo.posts.domain.repository.CommentRepository
import com.sanogueralorenzo.posts.domain.repository.PostRepository
import com.sanogueralorenzo.posts.domain.repository.UserRepository
import com.sanogueralorenzo.posts.domain.usecase.CommentsUseCase
import com.sanogueralorenzo.posts.domain.usecase.UserPostUseCase
import com.sanogueralorenzo.posts.domain.usecase.UsersPostsUseCase
import com.sanogueralorenzo.posts.presentation.postdetails.PostDetailsViewModel
import com.sanogueralorenzo.posts.presentation.postlist.PostListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

val viewModelModule: Module = module {
    viewModel { PostListViewModel(usersPostsUseCase = get()) }
    viewModel { PostDetailsViewModel(userPostUseCase = get(), commentsUseCase = get()) }
}

val useCaseModule: Module = module {
    factory { UsersPostsUseCase(userRepository = get(), postRepository = get()) }
    factory { UserPostUseCase(userRepository = get(), postRepository = get()) }
    factory { CommentsUseCase(commentRepository = get()) }
}

val repositoryModule: Module = module {
    single { UserRepositoryImpl(userApi = get(), cache = get(USER_ENTITY_CACHE)) as UserRepository }
    single { PostRepositoryImpl(postsApi = get(), cache = get(POST_ENTITY_CACHE)) as PostRepository }
    single { CommentRepositoryImpl(commentsApi = get(), cache = get(COMMENT_ENTITY_CACHE)) as CommentRepository }
}

val networkModule: Module = module {
    single { usersApi }
    single { postsApi }
    single { commentsApi }
}

val cacheModule: Module = module {
    single(name = USER_ENTITY_CACHE) { Cache<List<UserEntity>>() }
    single(name = POST_ENTITY_CACHE) { Cache<List<PostEntity>>() }
    single(name = COMMENT_ENTITY_CACHE) { Cache<List<CommentEntity>>() }
}

private const val BASE_URL = "http://jsonplaceholder.typicode.com/"

private val retrofit: Retrofit = createNetworkClient(BASE_URL, BuildConfig.DEBUG)

private val postsApi: PostsApi = retrofit.create(PostsApi::class.java)
private val usersApi: UsersApi = retrofit.create(UsersApi::class.java)
private val commentsApi: CommentsApi = retrofit.create(CommentsApi::class.java)

private const val USER_ENTITY_CACHE = "USER_ENTITY_CACHE"
private const val POST_ENTITY_CACHE = "POST_ENTITY_CACHE"
private const val COMMENT_ENTITY_CACHE = "COMMENT_ENTITY_CACHE"
