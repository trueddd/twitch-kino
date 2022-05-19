plugins {
    kotlin("multiplatform") version Versions.Kotlin
    id("org.jetbrains.compose") version Versions.Compose
}

group = Config.PackageName
version = Config.Version

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    js(IR) {
        browser {
        }
        binaries.executable()
    }
    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(compose.web.core)
                implementation(compose.runtime)
                implementation(jsStdlib)
                implementation(coroutines)
                implementation(project(":api"))
            }
        }
    }
}

tasks.getByName("jsBrowserWebpack").apply {
    dependsOn(":content:jsBrowserWebpack")
    doLast {
        copy {
            from("content/build/distributions/content.js")
            into("build/distributions")
        }
    }
}
