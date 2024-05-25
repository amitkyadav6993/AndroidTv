plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "amit.yadav.tvpiptask"
    compileSdk = 34

    defaultConfig {
        applicationId = "amit.yadav.tvpiptask"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "BASE_URL", "\"https://api.cricapi.com/v1/\"")
        buildConfigField("String", "MATCH_API_KEY", "\"c1cc584a-a676-4521-8a74-fe0dd2b45e1a\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.leanback)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.koin)
    implementation(libs.startup)
    implementation(libs.constriaintLayout)
    implementation(libs.cardview)
    implementation(libs.glide)

    implementation(project(":0-domain"))
    implementation(project(":1-data"))
    implementation(project(":1-data:1A-network"))
    implementation(project(":2-usecase"))
}
