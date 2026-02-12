
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.safeargs)
    alias(libs.plugins.firbaseservice)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias (libs.plugins.room)

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
    buildFeatures {
        compose = true
    }
}
room {
    schemaDirectory(layout.projectDirectory.dir("schemas"))
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
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.firebase.firestore)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    implementation(libs.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    annotationProcessor ("androidx.room:room-compiler:2.8.4")

    androidTestImplementation(libs.espresso.core)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}