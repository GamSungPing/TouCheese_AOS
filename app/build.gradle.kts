import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)
    id("com.google.gms.google-services")
}

val properties = Properties()
properties.load(project.rootProject.file("local.properties").inputStream())
val kakaoApiKey = properties.getProperty("kakao_api_key") ?: ""
val kakaoNativeKey = properties.getProperty("kakao_native_key") ?: ""

android {
    namespace = "com.example.toucheese"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.toucheese"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "KAKAO_API_KEY", "\"$kakaoApiKey\"")
        buildConfigField("String", "KAKAO_NATIVE_KEY", "\"$kakaoNativeKey\"")
        manifestPlaceholders["KAKAO_NATIVE_KEY"] = kakaoNativeKey
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
        buildConfig = true
    }
}

dependencies {

    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":presentation"))

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

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics.ktx)
    implementation(libs.firebase.messaging.ktx)
    implementation(libs.kakao.user)
}