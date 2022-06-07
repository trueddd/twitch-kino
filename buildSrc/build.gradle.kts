plugins {
    `kotlin-dsl`
    kotlin("plugin.serialization") version "1.6.20"
}

repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.0.1")
}
