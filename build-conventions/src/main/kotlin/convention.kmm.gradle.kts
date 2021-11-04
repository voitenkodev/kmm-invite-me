import com.android.build.gradle.BaseExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("native.cocoapods")
    checkstyle
}

version = "1.0"

configure<KotlinMultiplatformExtension> {
    explicitApi()

    android("android")
    iOS("ios")
    linuxX64("linux")
    macosX64("macos")
    mingwX64("windows")

    cocoapods {
        ios.deploymentTarget = "14.1"
        podfile = project.file("${rootProject}/apps/ios/Podfile")
        framework { baseName = this.project.name }
        homepage = "stub"
        summary = "stub"
    }

    sourceSets {
        val commonMain by getting
        val androidMain by getting
        val iosMain by getting
        val linuxMain by getting
        val macosMain by getting
        val windowsMain by getting
    }
}

fun KotlinMultiplatformExtension.iOS(name: String) {
    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        when {
            System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
            System.getenv("NATIVE_ARCH")?.startsWith("arm") == true -> ::iosSimulatorArm64
            else -> ::iosX64
        }
    iosTarget(name) {}
}

configure<BaseExtension> {
    compileSdkVersion(31)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 30
    }
}