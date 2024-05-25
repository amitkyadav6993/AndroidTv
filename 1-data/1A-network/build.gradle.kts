plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-parcelize")
}

android {
    namespace = "amit.yadav.network"
    compileSdk = 34

    defaultConfig {
        minSdk = 26
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.moshi.kotlin)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.koin)
    implementation(libs.okhttp)
    implementation(libs.retrofit)
    implementation(libs.converter.moshi)

    api(project(":0-domain"))
}