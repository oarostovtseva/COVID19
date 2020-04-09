@file:Suppress("unused")

// All val are used as dependencies

/**
 * Contains dependencies are used in project
 */

object Versions {
    const val build_tools = "28.0.3"
    const val target_sdk = 28
    const val compile_sdk = target_sdk
    const val min_sdk = "21"
    const val versionCode = 1
    const val versionName = "0.0.1"

    const val kotlin = "1.3.71"
}

object Plugins {
    private const val android_gradle_plugin = "3.6.2"
    private const val versions_gradle_plugin = "0.28.0"

    const val android_gradle = "com.android.tools.build:gradle:$android_gradle_plugin"
    const val kotlin_gradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val versions_gradle = "com.github.ben-manes:gradle-versions-plugin:$versions_gradle_plugin"
}

object Kotlin {
    private const val coroutines_version = "1.2.2"

    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version"
    const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version"
}

object AndroidX {
    private const val androidx_version = "1.2.0"
    private const val androidx_material_version = "1.1.0"
    private const val androidx_test_version = "1.2.0"
    private const val constraint_layout_version = "1.1.3"

    const val core = "androidx.core:core-ktx:$androidx_version"
    const val app_compat = "androidx.appcompat:appcompat:$androidx_version"
    const val constraint_layout = "androidx.constraintlayout:constraintlayout:$constraint_layout_version"
    const val design = "com.google.android.material:material:$androidx_material_version"
}

object Lifecycle {
    private const val lifecycle_version = "2.2.0"

    const val extensions = "androidx.lifecycle:lifecycle-extensions:$lifecycle_version"
    const val compiler = "androidx.lifecycle:lifecycle-compiler:$lifecycle_version"
    const val viewmodel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
    const val livedata_ktx = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"
}

object Koin {
    private const val koin_version = "2.1.5"

    const val viewmodel = "org.koin:koin-androidx-viewmodel:$koin_version"
    const val core = "org.koin:koin-core:$koin_version"
    const val android = "org.koin:koin-android:$koin_version"
}

object Networking {
    private const val retrofit_version = "2.8.1"
    private const val logging_interceptor_version = "4.5.0"

    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofit_version"
    const val retrofit_converter_moshi = "com.squareup.retrofit2:converter-moshi:$retrofit_version"
    const val okhttp_logging_interceptor = "com.squareup.okhttp3:logging-interceptor:$logging_interceptor_version"
}


object Timber {
    private const val timber_version = "4.7.1"

    const val timber = "com.jakewharton.timber:timber:$timber_version"
}

