package com.sanogueralorenzo.posts

import com.sanogueralorenzo.posts.data.cache.Cache
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
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val postListModule: Module = module {
    viewModel { PostListViewModel(get()) }
    factory { UsersPostsUseCase(get(), get()) }
}

val postDetailsModule: Module = module {
    viewModel { PostDetailsViewModel(get(), get()) }
    factory { UserPostUseCase(get(), get()) }
    factory { CommentsUseCase(get()) }
}

val repositoryModule: Module = module {
    single { UserRepositoryImpl(get(), get("UserEntityCache")) as UserRepository }
    single { PostRepositoryImpl(get(), get("PostEntityCache")) as PostRepository }
    single { CommentRepositoryImpl(get(), get("CommentEntityCache")) as CommentRepository }
}

val networkModule: Module = module {
    single { retrofit }
    single { usersApi }
    single { postsApi }
    single { commentsApi }
}

val cacheModule: Module = module {
    single(name = "UserEntityCache") { Cache<List<UserEntity>>() }
    single(name = "PostEntityCache") { Cache<List<PostEntity>>() }
    single(name = "CommentEntityCache") { Cache<List<CommentEntity>>() }
}

private val httpClient: OkHttpClient by lazy {
    val timeout = 10L

    val httpLoggingInterceptor =
        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
    val clientBuilder = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(httpLoggingInterceptor)
    }
    clientBuilder.connectTimeout(timeout, TimeUnit.SECONDS)
    clientBuilder.writeTimeout(timeout, TimeUnit.SECONDS)
    clientBuilder.readTimeout(timeout, TimeUnit.SECONDS)
    clientBuilder.build()
}

private val retrofit: Retrofit by lazy {
    val baseUrl = "http://jsonplaceholder.typicode.com/"

    Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}

private val postsApi: PostsApi by lazy { retrofit.create(PostsApi::class.java) }

private val usersApi: UsersApi by lazy { retrofit.create(UsersApi::class.java) }

private val commentsApi: CommentsApi by lazy { retrofit.create(CommentsApi::class.java) }
