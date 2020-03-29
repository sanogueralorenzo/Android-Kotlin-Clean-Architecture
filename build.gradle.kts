buildscript {
    repositories {
        maven { url = uri("https://maven.fabric.io/public") }
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.gradle}")
        classpath("com.google.gms:google-services:${Versions.googleServices}")
        classpath("io.fabric.tools:gradle:${Versions.fabric}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
    }
}

plugins {
    id("com.github.ben-manes.versions").version("0.28.0")
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
