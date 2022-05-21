plugins {
    kotlin("multiplatform") version Versions.Kotlin
    kotlin("plugin.serialization") version Versions.Kotlin
}

group = Config.PackageName
version = Config.Version

repositories {
    mavenCentral()
}

kotlin {
    js(IR) {
        browser {
            webpackTask {
            }
        }
    }
    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(jsStdlib)
                implementation(coroutines)
                implementation(ktorSerialization)
            }
        }
    }
}