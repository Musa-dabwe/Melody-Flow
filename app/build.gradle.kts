
plugins {
    id("com.android.application")
    
  id("kotlin-android")
  
  
}

android {
    namespace = "com.musa.melodyflow"
    compileSdk = 35
    
    
    defaultConfig {
        applicationId = "com.musa.melodyflow"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        
        vectorDrawables { 
            useSupportLibrary = true
        }
        
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    
    buildFeatures {
        viewBinding = true
        
    }
    
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.fromTarget("17"))
    }
}

dependencies {

    // Core
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.fragment:fragment-ktx:1.8.0")

    // Material Design
    implementation("com.google.android.material:material:1.12.0")

    // Navigation Component
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")

    // RecyclerView (for lists)
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    // Preferences (for Settings screen)
    implementation("androidx.preference:preference-ktx:1.2.1")

    // Image Loading (Glide)
    implementation("com.github.bumptech.glide:glide:4.16.0")

    // Media Playback (Media3)
    implementation("androidx.media3:media3-exoplayer:1.3.1")
    implementation("androidx.media3:media3-ui:1.3.1")
    implementation("androidx.media3:media3-session:1.3.1")
}
