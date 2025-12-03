plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "br.com.hellodev.di"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    // Domain
    implementation(project(":domain"))

    // Core
    implementation(project(":core"))

    // Data
    implementation(project(":data"))

    // Features - Onboarding
    implementation(project(":features:onboarding"))

    // Features - Authentication
    implementation(project(":features:authentication"))

    // Features - Setup
    implementation(project(":features:setup"))

    // Features - Main
    implementation(project(":features:main"))

    // Features - Profile
    implementation(project(":features:profile"))

    // Features - Job Search
    implementation(project(":features:job-search"))

    // Features - Job Details
    implementation(project(":features:job-details"))

    // Koin
    implementation(libs.koin.compose)
}