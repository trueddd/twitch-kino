plugins {
    kotlin("multiplatform") version Versions.Kotlin
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
        binaries.executable()
    }
    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(jsStdlib)
                implementation(coroutines)
                implementation(project(":api"))
            }
        }
    }
}
