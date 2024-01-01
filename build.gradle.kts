@Suppress("DSL_SCOPE_VIOLATION") plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidDynamicFeature) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.devtoolsKsp) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.secretsGradlePlugin) apply false
}
true
