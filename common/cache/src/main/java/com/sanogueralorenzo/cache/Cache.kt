package com.sanogueralorenzo.cache

import com.pacoworks.rxpaper2.RxPaperBook
import io.reactivex.Completable
import io.reactivex.Single

class Cache {

    fun <T> load(key: String): Single<T> = RxPaperBook.with().read(key)

    fun <T> save(key: String, data: T): Single<T> =
        RxPaperBook.with().write(key, data).toSingleDefault(data)

    fun delete(key: String): Completable = RxPaperBook.with().delete(key)
}
