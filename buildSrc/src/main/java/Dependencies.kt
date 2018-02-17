object Versions {
    val gradle = "3.0.1"

    val compile_sdk = 27
    val min_sdk = 21
    val target_sdk = 27

    val inject = "1"

    val support = "27.0.2"
    val maps = "11.8.0"

    val kotlin = "1.2.21"
    val anko = "0.10.4"
    val timber = "4.6.0"
    val rxandroid = "2.0.1"
    val rxkotlin = "2.2.0"
    val retrofit = "2.3.0"
    val logginginterceptor = "3.9.1"
    val dagger = "2.14.1"
    val glide = "4.4.0"
    val rxpaper = "1.1.0"
    val moshi = "1.4.0"

    val junit = "4.12"
    val assertj = "3.8.0"
    val mockitokotlin = "1.5.0"
    val robolectric = "3.5.1"

    val runner = "1.0.1"
    val espresso = "3.0.1"
}

object ApplicationId{
    val application_id = "com.sanogueralorenzo.androidkotlincleanarchitecture"
}

object Releases {
    val version_code = 1
    val version_name = "1.0"
}

object Libraries {
    val inject = "javax.inject:javax.inject:${Versions.inject}"

    val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    val anko_commons = "org.jetbrains.anko:anko-commons:${Versions.anko}"

    val maps = "com.google.android.gms:play-services-maps:${Versions.maps}"

    val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    val rxandroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxandroid}"
    val rxkotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxkotlin}"

    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val rxjava_adapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    val moshi_converter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    val logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.logginginterceptor}"

    val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    val dagger_compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"

    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    val rxpaper = "com.github.pakoito:RxPaper2:${Versions.rxpaper}"
    val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"

}

object SupportLibraries {
    val appcompat_v7 = "com.android.support:appcompat-v7:${Versions.support}}"
    val design = "com.android.support:design:${Versions.support}"
    val cardview_v7 = "com.android.support:cardview-v7:${Versions.support}"
    val recyclerview_v7 = "com.android.support:recyclerview-v7:${Versions.support}"
}

object TestLibraries {
    val junit = "junit:junit:${Versions.junit}"
    val assertj_core = "org.assertj:assertj-core:${Versions.assertj}"
    val mockito_kotlin = "com.nhaarman:mockito-kotlin:${Versions.mockitokotlin}"
    val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"

    val runner = "com.android.support.test:runner:${Versions.runner}"
    val espresso_core = "com.android.support.test.espresso:espresso-core:${Versions.espresso}"
}