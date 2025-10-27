plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias { libs.plugins.hilt }
    id("kotlin-kapt")
}

android {
    namespace = "com.amranjan.assignment.domain"
    compileSdk = 36

    defaultConfig {
        minSdk = 30

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {


    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.gson)
    testImplementation(libs.junit)
    testImplementation(libs.mokito)
    testImplementation(libs.turbine)
    testImplementation(libs.coroutineTest)
    testImplementation(kotlin("test"))

}