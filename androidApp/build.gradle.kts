@Suppress("UnstableApiUsage", "DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.app)
    alias(libs.plugins.kotlin.android)
}

dependencies {
    implementation(project(":shared"))

    implementation("io.github.voitenkodev:mvi-core:1.0.1")

    implementation(libs.kotlinx.kotlin)
    implementation(libs.kotlinx.coroutines)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.runtime)

    implementation(libs.koin.core)
    implementation(libs.koin.android)

    implementation(libs.coil.core)
    implementation(libs.coil.tooling)

    implementation(libs.compose.ui)
    implementation(libs.compose.tooling)
    implementation(libs.compose.preview)
    implementation(libs.compose.activity)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material)
    implementation(libs.compose.icons)
    implementation(libs.compose.icons.extensions)
    implementation(libs.compose.navigation)
    implementation(libs.compose.accompanist.animation)
    implementation(libs.compose.accompanist.insets)
    implementation(libs.compose.accompanist.system)
    implementation(libs.compose.viewmodel)
}


android {
    compileSdk = 31
    defaultConfig {
        applicationId = "com.voitenko.dev.kmminviteme.android"
        minSdk = 21
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes.getByName("release") { isMinifyEnabled = false }

    buildFeatures.compose = true
    composeOptions.kotlinCompilerExtensionVersion = "1.0.4"

    ignoreExperimental()
}

fun com.android.build.gradle.internal.dsl.BaseAppModuleExtension.ignoreExperimental() =
    kotlinOptions {
        freeCompilerArgs = listOf(
            "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-Xuse-experimental=androidx.compose.material.ExperimentalMaterialApi",
            "-Xuse-experimental=androidx.compose.animation.ExperimentalAnimationApi",
            "-Xuse-experimental=androidx.compose.ui.ExperimentalComposeUiApi",
            "-Xuse-experimental=androidx.compose.foundation.ExperimentalFoundationApi",
        )
    }