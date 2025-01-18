plugins {
    with(libs.plugins) {
        alias(com.android.application)
        alias(org.jetbrains.kotlin.android)
        alias(kotlin.parcelize)
        alias(kotlin.serialize)
        alias(ksp)
        alias(kotlin.kapt)
        alias(com.google.dagger.hilt.android)
        alias(androidx.navigation.safeargs)
        alias(com.gms.google.services)

        alias(com.google.firebase.crashlytics)
        alias(com.google.firebase.appdistribution)
//        alias(libs.plugins.play.publisher)
    }
}

android {
    namespace = "com.lazydeveloper.fit4u2"
    compileSdk = libs.versions.compileSdkVersion.get().toInt()
    
    defaultConfig {
        applicationId = "com.lazydeveloper.fit4u2"
        minSdk = libs.versions.minSdkVersion.get().toInt()
        targetSdk = libs.versions.targetSdkVersion.get().toInt()
        versionCode = (project.findProperty("BUILD_NUMBER")?.toString()?.toIntOrNull() ?: 0).coerceAtLeast(libs.versions.appVersionCode.get().toInt())
        versionName = libs.versions.appVersionName.get()
        
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        ndk {
            debugSymbolLevel = "FULL"
//            Specifies the ABI configurations of your native
//            libraries Gradle should build and package with your app.
            abiFilters += listOf("arm64-v8a", "armeabi-v7a", "x86", "x86_64")
        }
    }
    
    sourceSets {
        getByName("main") {
            jniLibs.srcDir("src/main/libs")
        }
    }
    
    flavorDimensions += listOf("lib")
    
    productFlavors {
        create("mobile") {
            dimension = "lib"
            buildConfigField("int", "DEVICE_TYPE", "1")
        }
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    
    kotlinOptions {
//        useIR = true
        jvmTarget = "17"
    }
    
    buildFeatures {
        compose = true
        buildConfig = true
        dataBinding = true
        viewBinding = true
    }
    
    signingConfigs {
        create("release") {
            if (project.hasProperty("TSPORTS_KEYSTORE_FILE")) {
                storeFile = file(project.findProperty("TSPORTS_KEYSTORE_FILE").toString())
                storePassword = project.findProperty("TSPORTS_KEYSTORE_PASSWORD")?.toString()
                keyAlias = project.findProperty("TSPORTS_KEY_ALIAS")?.toString()
                keyPassword = project.findProperty("TSPORTS_KEY_PASSWORD")?.toString()
            }
        }
    }
    
    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isShrinkResources = false
            ndk {
//            debugSymbolLevel = "FULL"
//            Specifies the ABI configurations of your native
//            libraries Gradle should build and package with your app.
                abiFilters += listOf("arm64-v8a", "armeabi-v7a", "x86", "x86_64")
            }
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("release") {
//            firebaseAppDistribution {
//                groups = "Testers"
//                releaseNotesFile="distribution/whatsnew/whatsnew-en-US"  // ignore this if releaseNotes is being used
//                releaseNotes="Release notes for demo version"  // ignore this if releaseNotesFile is being used
//                testers="ali@example.com, bri@example.com, cal@example.com"  // ignore this if groups is being used
//            }
            isMinifyEnabled = true
            isShrinkResources = true
            ndk {
//            debugSymbolLevel = "FULL"
//            Specifies the ABI configurations of your native
//            libraries Gradle should build and package with your app.
                abiFilters += listOf("arm64-v8a", "armeabi-v7a", "x86", "x86_64")
            }
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    
    packaging {
        jniLibs {
            useLegacyPackaging = true
            excludes += listOf(
                "lib/*/librsjni.so",
                "lib/*/libRSSupport.so",
                "lib/*/librsjni_androidx.so"
            )
        }
        resources {
            excludes += listOf(
                "META-INF/*.kotlin_module",
                "/META-INF/{AL2.0,LGPL2.1}"
            )
            pickFirsts += listOf(
                "META-INF/services",
                "META-INF/LICENSE",
                "META-INF/INDEX.LIST",
                "META-INF/io.netty.versions.properties",
                "META-INF/annotation-experimental_release.kotlin_module"
            )
        }
    }
    
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlin.compose.version.get()
    }
    
    lint {
        abortOnError = false
        checkReleaseBuilds = false
    }
}

//play {
//    serviceAccountCredentials = file('service-account.json')
//    track = "internal" // Release track to publish to (e.g., internal, alpha, beta, production)
//    defaultToAppBundles = true // Publish AAB files by default
//}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))
    implementation(files("libs/sslCommerzSdk.aar"))
    implementation(project(":data"))
    implementation(libs.googleid)
    implementation(libs.firebase.bom)

    with(libs) {
        // View
        implementation(activity)
//        implementation(fragment.ktx)
        implementation(splashscreen)
        implementation(material)
        implementation(cardview)
//        implementation(webkit)
//        implementation(switch.button)
//        implementation(circleimageview)
//        implementation(constraint.layout)
        implementation(legacy.support.v4)
        // Kotlin
        implementation(kotlin.stdlib)
        implementation(core.ktx)
        implementation(kotlin.coroutines)
        implementation(kotlin.gson)
        // Hilt
        implementation(bundles.hilt)
        ksp(hilt.android.compiler)
        ksp(hilt.compiler)
        ksp(hilt.compiler.kapt)
        // Jetpack
//        ksp(room.kapt)
        implementation(paging)
        implementation(bundles.room)
        implementation(platform(compose.bom))
        implementation(bundles.compose)
        implementation(work.manager.ktx)
        implementation(bundles.lifecycle)
        implementation(bundles.navigation)
        // Image
        implementation(bundles.coil)
//        implementation(bundles.image.crop)
        // Player
//        implementation(bundles.exoplayer)
//        implementation(bundles.cast)
        implementation(bundles.ads)
        // Network
//        implementation(bundles.retrofit)
//    implementation(net.gotev.uploadservice) {
//        exclude(group = "org.apache.httpcomponents", module = "httpclient")
//    }
//    implementation(net.gotev.uploadservice.okhttp)
        // Google Services
        implementation(google.api.client) {
            exclude(group = "org.apache.httpcomponents", module = "httpclient")
            exclude(group = "com.google.code.findbugs")
            exclude(module = "support-annotations")
            exclude(group = "com.google.guava")
        }
        implementation(google.http.client) {
            exclude(group = "org.apache.httpcomponents", module = "httpclient")
            exclude(group = "com.google.code.findbugs")
            exclude(module = "support-annotations")
            exclude(group = "com.google.guava")
        }
        // Play Services
        implementation(bundles.play.services)
        // Firebase
        implementation(bundles.firebase)

        // Social Login
        implementation(bundles.social.login)

        //FacebookLogin
//        implementation(facebook.sdk)
        // Reporting
//        implementation(pub.sub) {
//            exclude(group = "com.google.code.findbugs")
//            exclude(group = "org.apache.httpcomponents")
//            exclude(group = "org.json")
//            exclude(module = "support-annotations")
//            exclude(group = "com.google.guava")
//        }
        //logger
        implementation(bundles.logger)
        /////// Testing
        testImplementation(junit.core)
        androidTestImplementation(junit.ktx)
//    debugImplementation (leakcanary)
    }
}