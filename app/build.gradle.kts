plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.alepagani.habitsappcourse"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.alepagani.habitsappcourse"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.alepagani.habitsappcourse.HiltTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        kotlinCompilerExtensionVersion = libs.versions.composeVersion.get()
    }
    packagingOptions {
        resources {
            excludes += listOf("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.ui)
    implementation(libs.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // Compose Navigation
    implementation(libs.androidx.navigation.compose)

    // Dagger Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)
    kapt(libs.androidx.hilt.compiler)

    // WorkManager
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.androidx.hilt.work)

    // Modules
    implementation(project(":authentication:authentication_presentation"))
    implementation(project(":authentication:authentication_data"))
    implementation(project(":authentication:authentication_domain"))
    implementation(project(":core:core_presentation"))
    implementation(project(":core:core_data"))
    implementation(project(":home:home_presentation"))
    implementation(project(":home:home_data"))
    implementation(project(":home:home_domain"))
    implementation(project(":onboarding:onboarding_presentation"))
    implementation(project(":onboarding:onboarding_data"))
    implementation(project(":onboarding:onboarding_domain"))
    implementation(project(":settings:settings_presentation"))

    // Testing
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    kaptAndroidTest(libs.hilt.android.compiler)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    testImplementation(libs.turbine)
    androidTestImplementation(libs.hilt.android.testing)
    testImplementation(libs.mockk)
    androidTestImplementation(libs.androidx.work.testing)
}
