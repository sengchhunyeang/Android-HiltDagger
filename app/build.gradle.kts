
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.myapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdk = 31
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.runtime.livedata)
    dependencies {
        // AndroidX Core
        implementation("androidx.core:core-ktx:1.10.1")

        // AndroidX Lifecycle
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.0")

        // AndroidX Activity Compose
        implementation("androidx.activity:activity-compose:1.8.0")

        // Jetpack Compose
        implementation("androidx.compose.ui:ui:1.6.0")
        implementation("androidx.compose.ui:ui-graphics:1.6.0")
        implementation("androidx.compose.ui:ui-tooling-preview:1.6.0")
        implementation("androidx.compose.material3:material3:1.0.1")

        // Hilt
        implementation("com.google.dagger:hilt-android:2.51")
        kapt("com.google.dagger:hilt-android-compiler:2.51")
        implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
        kapt("com.google.dagger:hilt-compiler:2.51") // This should match the Hilt version

        // Retrofit for network calls
        implementation("com.squareup.retrofit2:retrofit:2.9.0")
        implementation("com.squareup.retrofit2:converter-gson:2.9.0")

        // OkHttp
        implementation("com.squareup.okhttp3:okhttp:4.10.0")
        implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

        // Coroutines for asynchronous programming
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")

        // Kotlinx Serialization
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

        // Coil for image loading
        implementation("io.coil-kt:coil-compose:2.0.0")
        implementation (libs.androidx.runtime.livedata.vversion)
        // Testing
        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.1.5")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
        androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.6.0")
        debugImplementation("androidx.compose.ui:ui-tooling:1.6.0")
        debugImplementation("androidx.compose.ui:ui-test-manifest:1.6.0")
    }

}
kapt {
    correctErrorTypes = true
}
hilt {
    enableAggregatingTask = true
}
