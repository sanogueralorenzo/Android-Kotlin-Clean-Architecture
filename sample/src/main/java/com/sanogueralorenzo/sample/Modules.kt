package com.sanogueralorenzo.sample

import com.sanogueralorenzo.cache.ReactiveCache
import com.sanogueralorenzo.network.createNetworkClient
import com.sanogueralorenzo.sample.data.datasource.CommentCacheDataSource
import com.sanogueralorenzo.sample.data.datasource.CommentRemoteDataSource
import com.sanogueralorenzo.sample.data.datasource.PostCacheDataSource
import com.sanogueralorenzo.sample.data.datasource.PostRemoteDataSource
import com.sanogueralorenzo.sample.data.datasource.UserCacheDataSource
import com.sanogueralorenzo.sample.data.datasource.UserRemoteDataSource
import com.sanogueralorenzo.sample.data.repository.CommentRepositoryImpl
import com.sanogueralorenzo.sample.data.repository.PostRepositoryImpl
import com.sanogueralorenzo.sample.data.repository.UserRepositoryImpl
import com.sanogueralorenzo.sample.datasource.cache.CommentCacheDataSourceImpl
import com.sanogueralorenzo.sample.datasource.cache.PostCacheDataSourceImpl
import com.sanogueralorenzo.sample.datasource.cache.UserCacheDataSourceImpl
import com.sanogueralorenzo.sample.datasource.model.PostEntity
import com.sanogueralorenzo.sample.datasource.model.UserEntity
import com.sanogueralorenzo.sample.datasource.remote.CommentRemoteDataSourceImpl
import com.sanogueralorenzo.sample.datasource.remote.CommentsApi
import com.sanogueralorenzo.sample.datasource.remote.PostRemoteDataSourceImpl
import com.sanogueralorenzo.sample.datasource.remote.PostsApi
import com.sanogueralorenzo.sample.datasource.remote.UserRemoteDataSourceImpl
import com.sanogueralorenzo.sample.datasource.remote.UsersApi
import com.sanogueralorenzo.sample.domain.model.Comment
import com.sanogueralorenzo.sample.domain.repository.CommentRepository
import com.sanogueralorenzo.sample.domain.repository.PostRepository
import com.sanogueralorenzo.sample.domain.repository.UserRepository
import com.sanogueralorenzo.sample.domain.usecase.CommentsUseCase
import com.sanogueralorenzo.sample.domain.usecase.UserPostUseCase
import com.sanogueralorenzo.sample.domain.usecase.UsersPostsUseCase
import com.sanogueralorenzo.sample.presentation.postdetails.PostDetailsViewModel
import com.sanogueralorenzo.sample.presentation.postlist.PostListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

fun injectFeature() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(
        viewModelModule,
        useCaseModule,
        repositoryModule,
        dataSourceModule,
        networkModule,
        cacheModule
    )
}

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
    single { UserRepositoryImpl(cacheDataSource = get(), remoteDataSource = get()) as UserRepository }
    single { PostRepositoryImpl(cacheDataSource = get(), remoteDataSource = get()) as PostRepository }
    single { CommentRepositoryImpl(cacheDataSource = get(), remoteDataSource = get()) as CommentRepository }
}

val dataSourceModule: Module = module {
    single { UserCacheDataSourceImpl(cache = get(USER_CACHE)) as UserCacheDataSource }
    single { UserRemoteDataSourceImpl(api = usersApi) as UserRemoteDataSource }
    single { PostCacheDataSourceImpl(cache = get(POST_CACHE)) as PostCacheDataSource }
    single { PostRemoteDataSourceImpl(api = postsApi) as PostRemoteDataSource }
    single { CommentCacheDataSourceImpl(cache = get(COMMENT_CACHE)) as CommentCacheDataSource }
    single { CommentRemoteDataSourceImpl(api = commentsApi) as CommentRemoteDataSource }
}

val networkModule: Module = module {
    single { usersApi }
    single { postsApi }
    single { commentsApi }
}

val cacheModule: Module = module {
    single(name = USER_CACHE) { ReactiveCache<List<UserEntity>>() }
    single(name = POST_CACHE) { ReactiveCache<List<PostEntity>>() }
    single(name = COMMENT_CACHE) { ReactiveCache<List<Comment>>() }
}

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

private val retrofit: Retrofit = createNetworkClient(BASE_URL, BuildConfig.DEBUG)

private val postsApi: PostsApi = retrofit.create(PostsApi::class.java)
private val usersApi: UsersApi = retrofit.create(UsersApi::class.java)
private val commentsApi: CommentsApi = retrofit.create(CommentsApi::class.java)

private const val USER_CACHE = "USER_CACHE"
private const val POST_CACHE = "POST_CACHE"
private const val COMMENT_CACHE = "COMMENT_CACHE"
