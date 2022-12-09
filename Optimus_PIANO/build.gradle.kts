plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "ar.com.optimus.optimuspiano.android"
    compileSdk = 32
    defaultConfig {
        applicationId = "ar.com.optimus.optimuspiano.android"
        minSdk = 24
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
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

val ktor_version: String by project

dependencies {
    implementation(project(":shared"))
    implementation("androidx.compose.ui:ui:1.2.1")
    implementation("androidx.compose.ui:ui-tooling:1.2.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.2.1")
    implementation("androidx.compose.foundation:foundation:1.2.1")
    implementation("androidx.compose.material:material:1.2.1")
    implementation("androidx.activity:activity-compose:1.5.1")
    implementation("io.coil-kt:coil-compose:2.2.2")
// List of artifacts, e.g.:
    //implementation("io.ktor:ktor-server-core-jvm:2.2.1")
    //implementation("io.ktor:ktor-server-netty-jvm:2.2.1")
    //implementation("io.ktor:ktor-server-status-pages-jvm:2.2.1")
    //implementation("io.ktor:ktor-server-default-headers-jvm:2.2.1")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")

}