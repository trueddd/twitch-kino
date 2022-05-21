import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.create

val jsStdlib = "org.jetbrains.kotlin:kotlin-stdlib-js:${Versions.Kotlin}"

val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core-js:${Versions.Coroutines}"

val ktorClientCore = "io.ktor:ktor-client-core:${Versions.Ktor}"
val ktorContentNegotiation = "io.ktor:ktor-client-content-negotiation:${Versions.Ktor}"
val ktorSerialization = "io.ktor:ktor-serialization-kotlinx-json:${Versions.Ktor}"
