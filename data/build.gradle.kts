@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    kotlin("kapt")
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.com.google.dagger.hilt.android)
}

android {
    namespace = "com.weatherforecast.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        buildConfigField("String", "COUNTRIES_NOW_BASE_URL", property("COUNTRIES_NOW_BASE_URL") as String)
        buildConfigField("String", "OPEN_WEATHER_MAP_BASE_URL", property("OPEN_WEATHER_MAP_BASE_URL") as String)
        buildConfigField("String", "OPEN_WEATHER_MAP_ID", property("OPEN_WEATHER_MAP_ID") as String)
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    kapt {
        correctErrorTypes = true
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(libs.core.ktx)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.retrofit.logging.interceptor)
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
}