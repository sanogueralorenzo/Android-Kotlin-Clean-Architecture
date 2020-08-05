plugins {
    // https://plugins.gradle.org/plugin/com.gradle.enterprise
    id("com.gradle.enterprise").version("3.4")
}

gradleEnterprise {
    buildScan {
        // Accept the license agreement for com.gradle.build-scan plugin
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
        publishAlways()
    }
}

include(
    ":app",
    ":sample",
    ":navigation",
    ":common:cache",
    ":common:network",
    ":common:resources",
    ":common:views",
    ":features:onboarding",
    ":features:home",
    ":features:profile",
    ":libs:usermanager"
)
