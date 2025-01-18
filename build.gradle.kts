plugins {
    with(libs.plugins) {
        alias(ksp) apply false
        alias(kotlin.kapt) apply false
        alias(com.gms.google.services) apply false
        alias(com.google.firebase.crashlytics) apply false
        alias(com.android.library) apply false
        alias(org.jetbrains.kotlin.android) apply false
        alias(com.android.application) apply false
        alias(kotlin.parcelize) apply false
        alias(androidx.navigation.safeargs) apply false
        alias(com.google.dagger.hilt.android) apply false
        alias(com.google.firebase.appdistribution) apply false
//        alias(libs.plugins.play.publisher) apply false
    }
}

tasks.register("clean", Delete::class) {
    delete(layout.buildDirectory)
}