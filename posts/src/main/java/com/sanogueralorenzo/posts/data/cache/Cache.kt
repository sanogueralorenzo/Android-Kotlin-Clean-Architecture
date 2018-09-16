package com.sanogueralorenzo.posts.data.cache

import com.pacoworks.rxpaper2.RxPaperBook
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers.io

/**
 * Paper is a fast NoSQL-like storage for Java/Kotlin objects on Android with automatic schema migration support.
 * See: https://github.com/pakoito/RxPaper2
 */
class Cache<T> {

    fun load(key: String): Single<T> = RxPaperBook.with(io()).read(key)

    fun save(key: String, anyObject: T): Single<T> =
        RxPaperBook.with(io()).write(key, anyObject).toSingleDefault(anyObject)
}
