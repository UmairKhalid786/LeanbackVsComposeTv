plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.techlads.devfestdemo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.techlads.devfestdemo"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
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

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidComposeCompiler.get()
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.leanback)
    implementation(libs.androidx.appcompat)
    implementation(libs.lifecycle)
    implementation(libs.material)
    implementation (libs.androidx.constraintlayout)
    implementation (libs.androidx.legacy.support.v4)

    implementation(libs.coils.compose)
    implementation(libs.compose.preview)

    implementation(libs.androidx.tv.material)
    implementation(libs.androidx.tv.foundation)
    implementation(libs.androidx.activity.compose)


    implementation(libs.glide)
    implementation(libs.gson)
}