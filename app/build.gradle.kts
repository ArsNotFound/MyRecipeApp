plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "me.arsnotfound.myrecipeapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "me.arsnotfound.myrecipeapp"
        minSdk = 26
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

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.constraintlayout)
    implementation(libs.recyclerview)
    implementation(libs.fragment)
    implementation(libs.material)

    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)
    
    testImplementation(libs.junit)

    androidTestImplementation(libs.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}