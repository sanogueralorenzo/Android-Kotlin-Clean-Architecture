buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(libs.androidGradle)
        classpath(libs.kotlinGradle)
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
