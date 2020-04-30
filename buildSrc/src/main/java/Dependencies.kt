object App {
    val mood = "com.sanogueralorenzo.namingishard"
    val sample = "com.sanogueralorenzo.sample"
}

object Modules {
    val app = ":app"
    val sample = ":sample:app"
    val navigation = ":navigation"

    val cache = ":common:cache"
    val network = ":common:network"

    val views = ":common:views"

    val home = ":features:home"
}

object Releases {
    const val versionCode = 1
    const val versionName = "1.0"
}

object DefaultConfig {
    const val minSdk = 24
    const val targetSdk = 29
    const val compileSdk = 29
}

object AndroidConfiguration {
    const val buildToolsVersion = "29.0.3"
}

object Versions {
    const val gradle = "3.6.2"
    const val kotlin = "1.3.72"
    const val paper = "2.6"
    const val rxPaper2 = "1.4.0"

    const val support = "1.1.0"
    const val constraint = "1.1.3"

    const val daggerVersion = "2.27"
    const val lifecycleVersion = "2.2.0"
    const val lifecycleTestingVersion = "2.1.0"
    const val glideVersion = "4.11.0"

    const val okhttpVersion = "4.3.1"

    const val junitVersion = "4.13"

    const val retrofitVersion = "2.7.1"
    const val mockk = "1.9.3"

    val leakCanary = "2.2"
    val playCore = "1.7.1"
}

object Deps {

    //Support Libraries
    const val appcompat = "androidx.appcompat:appcompat:${Versions.support}"
    const val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
    const val recyclerView = "androidx.recyclerview:recyclerview:1.1.0"
    const val design = "com.google.android.material:material:${Versions.support}"
    const val cardview = "androidx.cardview:cardview:${Versions.support}"
    const val swiperefreshlayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.0.0"
    const val core = "androidx.core:core-ktx:1.2.0"

    //Annotations
    const val assistedInject = "com.squareup.inject:assisted-inject-annotations-dagger2:0.5.2"
    const val assistedInjectProcessor =
        "com.squareup.inject:assisted-inject-processor-dagger2:0.5.2"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.daggerVersion}"
    const val daggerAndroidProcessor =
        "com.google.dagger:dagger-android-processor:${Versions.daggerVersion}"
    const val lifecycleCompiler =
        "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycleVersion}"

    const val junit = "junit:junit:${Versions.junitVersion}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"

    const val daggerAndroidSupport =
        "com.google.dagger:dagger-android-support:${Versions.daggerVersion}"

    const val converterMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofitVersion}"
    const val adapterRxJava2 = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofitVersion}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttpVersion}"

    const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
    const val rxJava2 = "io.reactivex.rxjava2:rxjava:2.2.19"
    const val rxAndroid2 = "io.reactivex.rxjava2:rxandroid:2.1.1"

    val leakCanaryAndroid = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"

    const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel:${Versions.lifecycleVersion}"
    const val lifecycleTesting =
        "androidx.arch.core:core-testing:${Versions.lifecycleTestingVersion}"

    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:1.8.0"

    const val paper = "io.paperdb:paperdb:${Versions.paper}"
    const val rxPaper2 = "com.github.pakoito:RxPaper2:${Versions.rxPaper2}"
    const val mvrx = "com.airbnb.android:mvrx:2.0.0-alpha2"
    const val mvrxTesting = "com.airbnb.android:mvrx-testing:2.0.0-alpha2"
    const val mvrxLauncher = "com.airbnb.android:mvrx-launcher:2.0.0-alpha2"

    val epoxy = "com.airbnb.android:epoxy:3.9.0"
    val epoxyProcessor = "com.airbnb.android:epoxy-processor:3.9.0"

    const val okhttpLoggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpVersion}"
    const val timber = "com.jakewharton.timber:timber:4.7.1"
    val playCore = "com.google.android.play:core:${Versions.playCore}"
}
