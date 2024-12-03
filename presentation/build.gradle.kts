plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)
    alias(libs.plugins.navigation.safe.args)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.example.presentation"
    compileSdk = 35

    defaultConfig {
        minSdk = 26

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    viewBinding{
        enable = true
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(platform(libs.compose.bom))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    implementation(libs.navigation.ktx)
    implementation(libs.navigation)

    implementation(libs.glide)
    kapt(libs.glide.compiler)

    implementation(libs.coroutines.core)
    implementation(libs.coroutines.core.jvm)

    implementation (libs.androidx.ui)

    implementation (libs.androidx.ui.tooling.preview)
    debugImplementation (libs.androidx.ui.tooling)

    implementation (libs.androidx.material.icons.core)
    implementation (libs.androidx.material.icons.extended)

    implementation (libs.androidx.activity.compose)
    implementation (libs.androidx.lifecycle.viewmodel.compose)
    implementation (libs.androidx.runtime.livedata)
    implementation (libs.androidx.material3)
    implementation (libs.material3)
    implementation (libs.coil.compose)
    implementation (libs.accompanist.pager)
    implementation (libs.accompanist.pager.indicators)

    implementation(libs.hilt.navigation.compose)
}