package com.palushkin.kotlintestapp.buildsrc

object Libs {

    const val androidGradlePlugin = "com.android.tools.build:gradle:4.1.2"

    const val material = "com.google.android.material:material:1.1.0"

    const val vectorDrawable = "androidx.vectordrawable:vectordrawable:1.1.0"

    const val playServicesAuth = "com.google.android.gms:play-services-auth:19.0.0"

    const val gson = "com.squareup.retrofit2:converter-gson:2.4.0"

    const val leakcanary = "com.squareup.leakcanary:leakcanary-android:2.7"

    const val glide = "com.github.bumptech.glide:glide:4.11.0"

    object Kotlin {
        private const val version = "1.4.21"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$version"
    }

    object Coroutines {
        private const val version = "1.4.2"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object RxJava {
        const val rxAndroid = "io.reactivex.rxjava2:rxandroid:2.0.1"
        const val rxJava = "io.reactivex.rxjava2:rxjava:2.2.19"
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.2.0"

        const val coreKtx = "androidx.core:core-ktx:1.3.2"

        const val workRuntimeKtx = "androidx.work:work-runtime-ktx:2.4.0"

        const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.0.4"

        const val legacySupport = "androidx.legacy:legacy-support-v4:1.0.0"

        const val recyclerview= "androidx.recyclerview:recyclerview:1.2.0-beta01"

        const val swiperefreshlayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"

        const val multidex = "androidx.multidex:multidex:2.0.1"

        object Room {
            private const val version = "2.2.6"
            const val runtime = "androidx.room:room-runtime:${version}"
            const val ktx = "androidx.room:room-ktx:${version}"
            const val compiler = "androidx.room:room-compiler:${version}"
        }

        object Lifecycle {
            private const val version = "2.2.0"
            const val extensions = "androidx.lifecycle:lifecycle-extensions:$version"
            const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val savedStateModule = "androidx.lifecycle:lifecycle-viewmodel-savedstate:$version"
            const val reactivestreamsKtx = "androidx.lifecycle:lifecycle-reactivestreams-ktx:$version"
        }

        object Navigation {
            private const val version = "2.3.3"
            const val fragmentKtx = "androidx.navigation:navigation-fragment-ktx:$version"
            const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:$version"
            const val navigationDynamicFeaturesFragment = "androidx.navigation:navigation-dynamic-features-fragment:$version"

            const val navigationSafeArgsGradlePlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
        }
    }

    object Dagger2 {
        private const val version = "2.28.1"
        const val dagger = "com.google.dagger:dagger:$version"
        const val compiler = "com.google.dagger:dagger-compiler:$version"
    }

    object Retrofit {
        const val converterMoshi = "com.squareup.retrofit2:converter-moshi:2.9.0"
        const val kotlinCoroutinesAdapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"
        const val moshiKotlinCodegen = "com.squareup.moshi:moshi-kotlin-codegen:1.11.0"
        const val moshi = "com.squareup.moshi:moshi:1.11.0"
        const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:1.11.0"

        const val retrofit = "com.squareup.retrofit2:retrofit:2.7.1"
    }

    object Tests {
        const val junit = "junit:junit:4.13"
        const val extJunit = "androidx.test.ext:junit:1.1.2"
        const val espressoCore = "androidx.test.espresso:espresso-core:3.3.0"
    }
}