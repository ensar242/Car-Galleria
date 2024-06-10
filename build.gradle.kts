buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(libs.google.services) // Assuming `libs.google.services` points to the correct version
        classpath("com.android.tools.build:gradle:7.4.1") // Adjust the version as needed
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0") // Adjust the version as needed
    }
}

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    id("com.google.gms.google-services") version "4.4.1" apply false // This can be specified here or as a classpath dependency above
}

