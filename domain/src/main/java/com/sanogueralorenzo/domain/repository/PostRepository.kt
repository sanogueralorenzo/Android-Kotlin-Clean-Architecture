package com.sanogueralorenzo.domain.repository

import com.sanogueralorenzo.domain.model.Post
import io.reactivex.Single

interface PostRepository {

    fun getRemote(): Single<List<Post>>

    fun getCache(): Single<List<Post>>
}
