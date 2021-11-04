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

enableFeaturePreview("VERSION_CATALOGS")
dependencyResolutionManagement {
    versionCatalogs { create("libs") { from(files("gradle/wrapper/libs.versions.toml")) } }
}