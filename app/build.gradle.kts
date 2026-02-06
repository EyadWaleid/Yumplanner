
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.safeargs)
    alias(libs.plugins.firbaseservice)

}

android {
    namespace = "com.example.yumplanner"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.yumplanner"
        minSdk = 24
        targetSdk = 36
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
}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.room)
    implementation(libs.glide)
    implementation(libs.converterGson)
    implementation(libs.credentials)
    implementation(libs.credentialsplay)
    implementation(libs.androidx.databinding.common)
    implementation(libs.rxadapter)
    implementation(libs.rxjava)
    implementation(libs.roomrx)
    implementation(libs.rxandroid)
    implementation(libs.googleid)
    implementation(platform(libs.firbassebom))
    implementation(libs.firebase)
    implementation(libs.lottie)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.fragment)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.recyclerview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    implementation(libs.core)

    annotationProcessor ("androidx.room:room-compiler:2.8.4")

    androidTestImplementation(libs.espresso.core)
}