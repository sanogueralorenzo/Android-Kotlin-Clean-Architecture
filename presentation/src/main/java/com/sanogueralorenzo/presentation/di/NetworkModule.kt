package com.sanogueralorenzo.presentation.di

import com.sanogueralorenzo.data.BuildConfig
import com.sanogueralorenzo.data.remote.CommentsApi
import com.sanogueralorenzo.data.remote.PostsApi
import com.sanogueralorenzo.data.remote.UsersApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    private val BASE_URL = "http://jsonplaceholder.typicode.com/"
    private val CONNECT_TIMEOUT = 10L
    private val READ_TIMEOUT = 10L
    private val WRITE_TIMEOUT = 10L

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        val clientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        clientBuilder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        clientBuilder.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        clientBuilder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        return clientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Provides
    fun providePostsApi(retrofit: Retrofit): PostsApi = retrofit.create(PostsApi::class.java)

    @Provides
    fun provideUsersApi(retrofit: Retrofit): UsersApi = retrofit.create(UsersApi::class.java)

    @Provides
    fun provideCommentsApi(retrofit: Retrofit): CommentsApi = retrofit.create(CommentsApi::class.java)

}
