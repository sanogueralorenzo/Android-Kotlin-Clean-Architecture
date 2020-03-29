object ApplicationId {
    val id = "com.sanogueralorenzo.namingishard"
}

object Modules {
    val app = ":app"
    val navigation = ":navigation"

    val cache = ":common:cache"
    val network = ":common:network"

    val presentation = ":common:presentation"

    val sample = ":sample"
}

object Releases {
    val versionCode = 1
    val versionName = "1.0"
}

object Versions {
    val gradle = "3.6.1"

    val compileSdk = 29
    val minSdk = 24
    val targetSdk = 29

    val googleAuth = "16.0.1"

    val googleServices = "4.3.3"

    val firebaseAuth = "16.0.4"
    val firebaseCore = "17.2.3"

    val fabric = "1.31.2"

    val appcompat = "1.2.0-alpha03"
    val design = "1.2.0-alpha05"
    val cardview = "1.0.0"
    val recyclerview = "1.2.0-alpha01"
    val swiperefreshlayout = "1.1.0-beta01"
    val maps = "15.0.1"

    val ktx = "1.0.0-alpha1"

    val kotlin = "1.3.71"
    val timber = "4.7.1"
    val rxjava = "2.2.19"
    val rxkotlin = "2.4.0"
    val retrofit = "2.8.1"
    val loggingInterceptor = "4.5.0-RC1"
    val glide = "4.11.0"
    val rxpaper = "1.4.0"
    val paperdb = "2.6"
    val moshi = "1.8.0"
    val lifecycle = "2.1.0"
    val leakCanary = "2.2"
    val crashlytics = "2.10.1"
    val koin = "2.0.0-beta-1"

    val playCore = "1.7.1"

    val junit = "4.13"
    val assertjCore = "3.15.0"
    val mockitoKotlin = "2.1.0"
    val mockitoInline = "3.3.3"
}

object Libraries {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    val ktx = "androidx.core:core-ktx:${Versions.ktx}"

    val maps = "com.google.android.gms:play-services-maps:${Versions.maps}"

    val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    val rxjava = "io.reactivex.rxjava2:rxjava:${Versions.rxjava}"
    val rxkotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxkotlin}"

    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val rxjavaAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"

    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    val paperdb = "io.paperdb:paperdb:${Versions.paperdb}"
    val rxpaper = "com.github.pakoito:RxPaper2:${Versions.rxpaper}"
    val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"

    val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"

    val leakCanaryAndroid = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"

    val crashlytics = "com.crashlytics.sdk.android:crashlytics:${Versions.crashlytics}"

    val koinAndroid = "org.koin:koin-android:${Versions.koin}"
    val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
}

object SupportLibraries {
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val design = "com.google.android.material:material:${Versions.design}"
    val cardview = "androidx.cardview:cardview:${Versions.cardview}"
    val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    val swiperefreshlayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swiperefreshlayout}"
}

object GoogleLibraries {
    val auth = "com.google.android.gms:play-services-auth:${Versions.googleAuth}"
    val playCore = "com.google.android.play:core:${Versions.playCore}"
}

object FirebaseLibraries {
    val auth = "com.google.firebase:firebase-auth:${Versions.firebaseAuth}"
    val core = "com.google.firebase:firebase-core:${Versions.firebaseCore}"
}

object TestLibraries {
    val junit = "junit:junit:${Versions.junit}"
    val assertjCore = "org.assertj:assertj-core:${Versions.assertjCore}"
    val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
    val mockitoInline = "org.mockito:mockito-inline:${Versions.mockitoInline}"
    val lifecycleTesting = "androidx.arch.core:core-testing:${Versions.lifecycle}"
}
