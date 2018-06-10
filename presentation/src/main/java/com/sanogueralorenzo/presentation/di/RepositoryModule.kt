package com.sanogueralorenzo.presentation.di

import com.sanogueralorenzo.data.repository.CommentRepositoryImpl
import com.sanogueralorenzo.data.repository.PostRepositoryImpl
import com.sanogueralorenzo.data.repository.UserRepositoryImpl
import com.sanogueralorenzo.domain.repository.CommentRepository
import com.sanogueralorenzo.domain.repository.PostRepository
import com.sanogueralorenzo.domain.repository.UserRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindPostRepository(repository: PostRepositoryImpl): PostRepository

    @Binds
    abstract fun bindUserRepository(repository: UserRepositoryImpl): UserRepository

    @Binds
    abstract fun bindCommentRepository(repository: CommentRepositoryImpl): CommentRepository
}
