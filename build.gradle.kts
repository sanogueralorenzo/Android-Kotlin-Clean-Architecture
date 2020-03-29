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
    id("io.gitlab.arturbosch.detekt").version("1.7.2")
    id("com.github.ben-manes.versions").version("0.28.0")
}

dependencies {
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.7.2")
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

val detektAll by tasks.registering(io.gitlab.arturbosch.detekt.Detekt::class) {
    description = "Runs over whole code base without the starting overhead for each module."
    autoCorrect = true
    parallel = true
    setSource(files(projectDir))
    include("**/*.kt")
    include("**/*.kts")
    exclude("**/build/**")
    exclude("**/buildSrc/**")
    exclude("**/test/**/*.kt")
    config.setFrom(files("$rootDir/detekt.yml"))
    baseline.set(file("$rootDir/detekt-baseline.xml"))
    reports {
        xml.enabled = false
        html.enabled = false
        txt.enabled = false
    }
}

val detektAllBaseline by tasks.registering(io.gitlab.arturbosch.detekt.DetektCreateBaselineTask::class) {
    description = "Overrides current baseline."
    ignoreFailures.set(true)
    parallel.set(true)
    setSource(files(rootDir))
    config.setFrom(files("$rootDir/detekt.yml"))
    baseline.set(file("$rootDir/detekt-baseline.xml"))
    include("**/*.kt")
    include("**/*.kts")
    exclude("**/build/**")
    exclude("**/buildSrc/**")
    exclude("**/test/**/*.kt")
}
