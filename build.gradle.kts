buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.com_android_tools_build_gradle}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.org_jetbrains_kotlin}")
    }
}

plugins {
    //    run with ./gradlew dependencyUpdates
    id("com.github.ben-manes.versions").version("0.38.0")
}

allprojects {
    repositories {
        maven { url = uri("https://maven.fabric.io/public") }
        maven { url = uri("https://jitpack.io") }
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
