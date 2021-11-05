plugins { `kotlin-dsl` }

version = "1.0"

gradlePlugin.plugins.register("library.kmm.sharing") {
    id = "library.kmm.sharing"
    implementationClass = "LibrarySharing"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions { jvmTarget = "11" }
}

repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
    implementation("com.android.tools.build:gradle:7.1.0-beta02")
}

buildscript {
    repositories {
        mavenCentral()
        google()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.1.0-beta02")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
    }
}
