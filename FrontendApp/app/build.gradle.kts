plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    //id("kotlin-kapt")
    //id("com.google.dagger.hilt.android")
    kotlin("plugin.serialization") version "1.9.0" // or the latest version.
}

android {
    namespace = "com.example.linux_logic_app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.linux_logic_app"
        minSdk = 24
        //noinspection OldTargetApi
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    // Externe Libraries
    // https://mvnrepository.com/artifact/androidx.compose.ui/ui-text-google-fonts
    implementation(libs.androidx.ui.text.google.fonts)
    // https://mvnrepository.com/artifact/androidx.navigation/navigation-compose
    implementation(libs.androidx.navigation.compose)
    // https://mvnrepository.com/artifact/androidx.compose.animation/animation
    implementation(libs.androidx.animation)
    // https://mvnrepository.com/artifact/androidx.compose.material/material-icons-extended
    implementation(libs.androidx.material.icons.extended)
    // https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp
    implementation(libs.okhttp)
    // https://mvnrepository.com/artifact/com.github.skydoves/colorpicker-compose
    implementation("com.github.skydoves:colorpicker-compose:1.1.2")


    implementation("io.ktor:ktor-client-core:2.0.0")
    implementation("io.ktor:ktor-client-cio:2.0.0") // Alternativ: OkHttp oder Android
    implementation("io.ktor:ktor-client-android:2.0.0") // Für Android-spezifischen Client
    implementation("io.ktor:ktor-client-content-negotiation:2.0.0")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.0.0")

    //https://developer.android.com/training/dependency-injection/hilt-android?hl=de#kts
    //implementation("com.google.dagger:hilt-android:2.51.1")
    //kapt("com.google.dagger:hilt-android-compiler:2.51.1")

    //https://www.youtube.com/watch?v=bbMsuI2p1DQ
    //implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
}

// Allow references to generated code
//kapt {
//correctErrorTypes = true
//}