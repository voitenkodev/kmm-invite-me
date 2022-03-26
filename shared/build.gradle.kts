import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

@Suppress("UnstableApiUsage", "DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.native)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kmm.implementation)
    alias(libs.plugins.sqldelight)
}

version = "1.0"

kotlin {
    explicitApi()
    useAndroid()
    useIos()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = false
        }
    }

    sourceSets {
        val commonMain by getting
        val androidMain by getting
        val iosMain by getting
    }
}

`kmm-implementation`.apply {
    implementation(common = libs.kotlinx.coroutines)
    implementation(common = libs.kotlinx.datetime)
    implementation(common = libs.kotlinx.serialization.json)
    implementations(
        common = listOf(libs.ktor.core, libs.ktor.logging, libs.ktor.serialization, libs.ktor.negotiation),
        android = listOf(libs.ktor.okhttp),
        ios = listOf(libs.ktor.ios)
    )
    implementations(
        common = listOf(libs.sqldelight.common, libs.sqldelight.extensions),
        android = listOf(libs.sqldelight.android),
        ios = listOf(libs.sqldelight.native)
    )
}

sqldelight {
    this.database("AppDataBase") {
        packageName = "com.voitenko.dev.kmminviteme.local"
        sourceFolders = listOf("kotlin")
        linkSqlite = true
    }
}

fun org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension.useAndroid() = android {
    this.project.android {
        compileSdk = 31
        sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
        defaultConfig {
            minSdk = 21
            targetSdk = 31
        }
    }
}

fun org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension.useIos() {
    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
        System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
        System.getenv("NATIVE_ARCH")?.startsWith("arm") == true -> ::iosSimulatorArm64
        else -> ::iosX64
    }

    iosTarget("ios") {}
}