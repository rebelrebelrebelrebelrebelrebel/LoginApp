plugins {
    id("com.android.application")
    id("com.google.gms.google-services") // Asegúrate de que Google Services esté correctamente configurado
    id("org.jetbrains.kotlin.android") version "1.8.10" // Kotlin plugin
}

android {
    namespace = "com.rebel.myloginapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.rebel.myloginapp"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
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

    // Configuración para Kotlin
    kotlinOptions {
        jvmTarget = "1.8" // Especifica la versión del JVM
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.activity:activity:1.7.2")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-auth:22.0.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.10") // Asegúrate de que la versión de Kotlin sea consistente
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.10") // Si es necesario
    implementation("androidx.navigation:navigation-fragment:2.5.3")
    implementation("androidx.navigation:navigation-ui:2.5.3")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
