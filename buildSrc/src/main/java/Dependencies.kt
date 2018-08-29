object ApplicationId {
    val id = "com.sanogueralorenzo.androidkotlincleanarchitecture"
}

object Modules {
    val domain = ":domain"
    val data = ":data"
}

object Releases {
    val versionCode = 1
    val versionName = "1.0"
}

object Versions {
    val gradle = "3.1.4"

    val compileSdk = 28
    val minSdk = 19
    val targetSdk = 28

    val inject = "1"

    val support = "28.0.0-rc01"
    val maps = "15.0.1"

    val kotlin = "1.2.60"
    val anko = "0.10.5"
    val timber = "4.7.1"
    val rxkotlin = "2.3.0"
    val retrofit = "2.4.0"
    val loggingInterceptor = "3.11.0"
    val dagger = "2.17"
    val glide = "4.7.1"
    val rxpaper = "1.2.0"
    val moshi = "1.4.0"
    val lifecycle = "1.1.1"

    val junit = "4.12"
    val assertjCore = "3.10.0"
    val mockitoKotlin = "1.5.0"
}

object Libraries {
    val inject = "javax.inject:javax.inject:${Versions.inject}"

    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val anko = "org.jetbrains.anko:anko-commons:${Versions.anko}"

    val maps = "com.google.android.gms:play-services-maps:${Versions.maps}"

    val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    val rxkotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxkotlin}"

    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val rxjavaAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"

    val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"

    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    val rxpaper = "com.github.pakoito:RxPaper2:${Versions.rxpaper}"
    val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"

    val lifecycleExtensions = "android.arch.lifecycle:extensions:${Versions.lifecycle}"
    val lifecycleCompiler = "android.arch.lifecycle:compiler:${Versions.lifecycle}"
}

object SupportLibraries {
    val appcompat = "com.android.support:appcompat-v7:${Versions.support}}"
    val design = "com.android.support:design:${Versions.support}"
    val cardview = "com.android.support:cardview-v7:${Versions.support}"
    val recyclerview = "com.android.support:recyclerview-v7:${Versions.support}"
}

object TestLibraries {
    val junit = "junit:junit:${Versions.junit}"
    val assertjCore = "org.assertj:assertj-core:${Versions.assertjCore}"
    val mockitoKotlin = "com.nhaarman:mockito-kotlin:${Versions.mockitoKotlin}"
    val lifecycleTesting = "android.arch.core:core-testing:${Versions.lifecycle}"
}