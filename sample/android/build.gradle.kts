import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "co.yml.ychat.android"
    compileSdk = Config.COMPILE_SDK_VERSION
    defaultConfig {
        applicationId = "co.yml.ychat.android"
        minSdk = Config.MIN_SDK_VERSION
        targetSdk = Config.TARGET_SDK_VERSION
        versionCode = 1
        versionName = "1.0"

        val key = "api.key"
        val apiKey = System
            .getenv()
            .getOrDefault(key, gradleLocalProperties(rootDir).getProperty(key))
        buildConfigField("String", "API_KEY", "\"$apiKey\"")
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":ychat"))
    implementation(Dependencies.UI.COMPOSE_UI)
    implementation(Dependencies.UI.COMPOSE_TOOLING)
    implementation(Dependencies.UI.COMPOSE_TOOLING_PREVIEW)
    implementation(Dependencies.UI.COMPOSE_FOUNDATION)
    implementation(Dependencies.UI.COMPOSE_MATERIAL)
    implementation(Dependencies.UI.COMPOSE_ACTIVITY)
    implementation(Dependencies.UI.COMPOSE_NAVIGATION)
    implementation(Dependencies.UI.COMPOSE_LIVEDATA)
    implementation(Dependencies.UI.COIL)
    implementation(Dependencies.DI.KOIN_CORE)
    implementation(Dependencies.DI.KOIN_ANDROID)
    implementation(Dependencies.DI.KOIN_COMPOSE)
}
