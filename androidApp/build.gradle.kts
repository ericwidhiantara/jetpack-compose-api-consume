plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
    id("com.google.devtools.ksp")

    id("com.google.dagger.hilt.android")

}

android {
    namespace = "com.example.composeapiconsume.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.example.composeapiconsume.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
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
    implementation(projects.shared)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.androidx.activity.compose)
    debugImplementation(libs.compose.ui.tooling)

    // Retrofit
    implementation(libs.retrofit)

    // Gson converter for Retrofit
    implementation(libs.converter.gson)

    // OkHttp for making HTTP requests
    implementation(libs.okhttp)

    // OkHttp Logging Interceptor (optional, for logging HTTP request/response)
    implementation(libs.logging.interceptor)

    // Arrow
    implementation(libs.arrow.core)

    // Room
    implementation(libs.androidx.room.runtime) // Check for the latest version
    ksp(libs.androidx.room.compiler) // Use KSP for annotation processing

    // For Hilt Dependency Injection
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    ksp(libs.androidx.hilt.compiler)

    // For ViewModel injection in Compose
    implementation(libs.androidx.hilt.navigation.compose)
}