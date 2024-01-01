@Suppress("DSL_SCOPE_VIOLATION") plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.devtoolsKsp)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.secretsGradlePlugin)
}

android {
    namespace = "com.promecarus.core"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = libs.versions.jvmTarget.get()
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlinCompilerExtensionVersion.get()
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // android database sqlcipher
    implementation(libs.android.database.sqlcipher)

    // coil
    implementation(libs.coil.compose)

    // compose
    implementation(platform(libs.compose.bom))
    implementation(libs.material)
    implementation(libs.material3)
    implementation(libs.ui)
    implementation(libs.ui.graphics)

    // datastore preferences
    implementation(libs.datastore.preferences)

    // koin
    implementation(libs.koin.androidx.compose)

    // leakcanary
    debugImplementation(libs.leakcanary.android)

    // lifecycle
    implementation(libs.lifecycle.viewmodel.compose)

    // navigation
    implementation(libs.navigation.compose)

    // network
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    // room
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    // sqlite
    implementation(libs.sqlite.ktx)
}
