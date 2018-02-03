package com.sanogueralorenzo.data.cache

import com.pacoworks.rxpaper2.RxPaperBook
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers.io
import javax.inject.Inject

/**
 * Paper is a fast NoSQL-like storage for Java/Kotlin objects on Android with automatic schema migration support.
 * See: https://github.com/pakoito/RxPaper2
 *
 * TODO: Implement https://github.com/pakoito/RxObservableDiskCache if it doesn't compromise readability and simplicity.
 */
class Cache<T> @Inject constructor() {

    fun save(key: String, anyObject: T): Single<T> = RxPaperBook.with(io()).write(key, anyObject).toSingleDefault(anyObject)

    fun load(key: String, defaultValue: T): Single<T> = RxPaperBook.with(io()).read(key, defaultValue)
}
