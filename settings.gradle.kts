pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "kmm-invite-me"
include(":androidApp")
include(":shared")
includeBuild("build-conventions")

enableFeaturePreview("VERSION_CATALOGS")
@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    versionCatalogs { create("libs") { from(files("gradle/wrapper/libs.versions.toml")) } }
}