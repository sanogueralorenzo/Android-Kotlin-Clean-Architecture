object App {
    val petproject = "com.sanogueralorenzo.namingishard"
    val sample = "com.sanogueralorenzo.sample"
}

object Modules {
    val app = ":app"

    val navigation = ":navigation"

    val resources = ":common:resources"
    val cache = ":common:cache"
    val network = ":common:network"
    val views = ":common:views"

    val onboarding = ":features:onboarding"
    val home = ":features:home"
    val profile = ":features:profile"

    val usermanager = ":libs:usermanager"
}

object Releases {
    val versionCode = 1
    val versionName = "1.0"
}

object DefaultConfig {
    val minSdk = 24
    val targetSdk = 29
    val compileSdk = 29
}

object Versions {
    val gradle = "4.0.0"
    val kotlin = "1.3.72"
    val rxpaper = "1.4.0"

    val support = "1.1.0"
    val constraint = "1.1.3"

    val daggerHilt = "2.28-alpha"

    val koin = "2.1.5"
    val lifecycle = "2.2.0"
    val lifecycleTesting = "2.1.0"
    val glide = "4.11.0"

    val okhttp = "4.7.2"

    val junit = "4.13"

    val lottie = "3.4.0"

    val retrofit = "2.9.0"
    val mockk = "1.10.0"

    val leakCanary = "2.3"
    val playCore = "1.7.3"

    val epoxy = "4.0.0-beta4"
}

object Deps {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    //Support Libraries
    val appcompat = "androidx.appcompat:appcompat:1.3.0-alpha01"
    val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
    val recyclerView = "androidx.recyclerview:recyclerview:1.2.0-alpha03"
    val core = "androidx.core:core-ktx:1.2.0"
    val design = "com.google.android.material:material:1.3.0-alpha01"
    val cardview = "androidx.cardview:cardview:${Versions.support}"
    val swiperefreshlayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0-rc01"

    val koin = "org.koin:koin-android-viewmodel:${Versions.koin}"

    val junit = "junit:junit:${Versions.junit}"
    val mockk = "io.mockk:mockk:${Versions.mockk}"

    val assistedInject = "com.squareup.inject:assisted-inject-annotations-dagger2:0.5.2"
    val assistedInjectProcessor = "com.squareup.inject:assisted-inject-processor-dagger2:0.5.2"

    val daggerHilt = "com.google.dagger:hilt-android:${Versions.daggerHilt}"
    val daggerHiltProcessor = "com.google.dagger:hilt-android-compiler:${Versions.daggerHilt}"

    val converterMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    val adapterRxJava2 = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"

    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val rxJava2 = "io.reactivex.rxjava2:rxjava:2.2.19"

    val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.6"
    val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.6"


    val leakCanaryAndroid = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"

    val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    val lifecycleTesting = "androidx.arch.core:core-testing:${Versions.lifecycleTesting}"

    val lottie = "com.airbnb.android:lottie:${Versions.lottie}"

    val rxpaper = "com.github.pakoito:RxPaper2:${Versions.rxpaper}"

    val mvrx = "com.airbnb.android:mvrx:2.0.0-alpha2"
    val mvrxTesting = "com.airbnb.android:mvrx-testing:2.0.0-alpha2"
    val mvrxLauncher = "com.airbnb.android:mvrx-launcher:2.0.0-alpha2"

    val epoxy = "com.airbnb.android:epoxy:${Versions.epoxy}"
    val epoxyProcessor = "com.airbnb.android:epoxy-processor:${Versions.epoxy}"

    val okhttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    val timber = "com.jakewharton.timber:timber:4.7.1"
    val playCore = "com.google.android.play:core:${Versions.playCore}"
}
