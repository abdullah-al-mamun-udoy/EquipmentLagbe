plugins {
    with(libs.plugins) {
        alias(com.android.library)
        alias(org.jetbrains.kotlin.android)
        alias(kotlin.parcelize)
        alias(ksp)
        alias(com.google.dagger.hilt.android)
    }
}

android {
    namespace = "com.metamamun.equipment.lib"
    compileSdk = libs.versions.compileSdkVersion.get().toInt()
    
    defaultConfig {
        minSdk = libs.versions.minSdkVersion.get().toInt()
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    
    flavorDimensions += listOf("lib")
    productFlavors {
        create("mobile") {
            dimension = "lib"
            
            val appVersionCode: String = libs.versions.appVersionCode.get()
            val appVersionName: String = libs.versions.appVersionName.get()
            
            buildConfigField("int", "DEVICE_TYPE", "1")
            buildConfigField("int", "APP_VERSION_CODE", appVersionCode)
            buildConfigField("String", "APP_VERSION_NAME", "\"$appVersionName\"")
        }
        create("tv") {
            dimension = "lib"
            
            val appVersionCode: String = libs.versions.appVersionCode.get()
            val appVersionName: String = libs.versions.appVersionName.get()
            
            buildConfigField("int", "DEVICE_TYPE", "4")
            buildConfigField("int", "APP_VERSION_CODE", appVersionCode)
            buildConfigField("String", "APP_VERSION_NAME", "\"$appVersionName\"")
        }
    }
    
    buildFeatures {
        buildConfig = true
    }
    
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    with(libs) {
        // Kotlin
        implementation(kotlin.coroutines)
        // Hilt
        implementation(bundles.hilt)
        ksp(hilt.android.compiler)
        ksp(hilt.compiler)
        ksp(hilt.compiler.kapt)
        // Jetpack
        ksp(room.kapt)
        implementation(paging)
        implementation(bundles.room)
        implementation(compose.activity)
        // Image
        implementation(coil.core)
        implementation(exifinterface)
        // Network
        implementation(gson)
        implementation(bundles.retrofit)
        implementation(okhttp.pretty.logger) {
            exclude(group = "org.json", module = "json")
        }
        // Google Services
//        implementation(google.api.client) {
//            exclude(group = "org.apache.httpcomponents", module = "httpclient")
//            exclude(group = "com.google.code.findbugs")
//            exclude(module = "support-annotations")
//            exclude(group = "com.google.guava")
//        }
//        implementation(google.http.client) {
//            exclude(group = "org.apache.httpcomponents", module = "httpclient")
//            exclude(group = "com.google.code.findbugs")
//            exclude(module = "support-annotations")
//            exclude(group = "com.google.guava")
//        }
        // Firebase
//        implementation(bundles.firebase)
        // Reporting
        implementation(bundles.mqtt)
        implementation(pub.sub) {
            exclude(group = "com.google.code.findbugs")
            exclude(group = "org.apache.httpcomponents", module = "httpclient")
            exclude(group = "org.json")
            exclude(module = "support-annotations")
            exclude(group = "com.google.guava")
        }
        /////// Testing
        testImplementation(junit.core)

        implementation(bundles.logger)
    }
}