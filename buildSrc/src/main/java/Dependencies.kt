object ApplicationId {
    val id = "com.sanogueralorenzo.namingishard"
}

object Modules {
    val cache = ":cache"
    val network = ":network"

    val presentation = ":presentation"

    val home = ":home"
    val login = ":login"
    val posts = ":posts"
}

object Releases {
    val versionCode = 1
    val versionName = "1.0"
}

object Versions {
    val gradle = "3.2.1"

    val compileSdk = 28
    val minSdk = 21
    val targetSdk = 28

    val googleServices = "4.0.1"
    val firebase = "16.0.4"
    val googleAuth = "16.0.0"

    val fabric = "1.26.1"

    val support = "28.0.0-rc02"
    val maps = "15.0.1"

    val ktx = "1.0.0-alpha1"

    val kotlin = "1.2.71"
    val timber = "4.7.1"
    val rxkotlin = "2.3.0"
    val retrofit = "2.4.0"
    val loggingInterceptor = "3.11.0"
    val dagger = "2.17"
    val glide = "4.8.0"
    val rxpaper = "1.2.0"
    val moshi = "1.4.0"
    val lifecycle = "1.1.1"
    val leakCanary = "1.6.1"
    val crashlytics = "2.9.5"
    val koin = "1.0.1"

    val junit = "4.12"
    val assertjCore = "3.11.1"
    val mockitoKotlin = "2.0.0-RC1"
    val mockitoInline = "2.23.0"
}

object Libraries {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    val ktx = "androidx.core:core-ktx:${Versions.ktx}"

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

    val leakCanaryAndroid = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
    val leakCanaryAndroidNoOp = "com.squareup.leakcanary:leakcanary-android-no-op:${Versions.leakCanary}"
    val leakCanaryAndroidSupportFragment = "com.squareup.leakcanary:leakcanary-support-fragment:${Versions.leakCanary}"

    val crashlytics = "com.crashlytics.sdk.android:crashlytics:${Versions.crashlytics}"

    val koinViewModel = "org.koin:koin-android-viewmodel:${Versions.koin}"
}

object SupportLibraries {
    val appcompat = "com.android.support:appcompat-v7:${Versions.support}}"
    val design = "com.android.support:design:${Versions.support}"
    val cardview = "com.android.support:cardview-v7:${Versions.support}"
    val recyclerview = "com.android.support:recyclerview-v7:${Versions.support}"
}

object GoogleLibraries {
    val auth = "com.google.android.gms:play-services-auth:${Versions.googleAuth}"
}

object FirebaseLibraries {
    val auth = "com.google.firebase:firebase-auth:${Versions.firebase}"
    val core = "com.google.firebase:firebase-core:${Versions.firebase}"
}

object TestLibraries {
    val junit = "junit:junit:${Versions.junit}"
    val assertjCore = "org.assertj:assertj-core:${Versions.assertjCore}"
    val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
    val mockitoInline = "org.mockito:mockito-inline:${Versions.mockitoInline}"
    val lifecycleTesting = "android.arch.core:core-testing:${Versions.lifecycle}"
}