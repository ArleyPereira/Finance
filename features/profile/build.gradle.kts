plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "br.com.hellodev.profile"
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

    // Design
    implementation(project(":design"))

    // Core
    implementation(project(":core"))

    // Feature - Credit Card
    implementation(project(":features:credit_card"))

    // Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.navigation.compose)

    // Koin
    implementation(libs.koin.compose)

    // Serialization
    implementation(libs.serialization.json)

    // Debug
    debugImplementation(libs.compose.ui.tooling)
}