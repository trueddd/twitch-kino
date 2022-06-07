import tasks.manifest.*

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

val updateManifest = tasks.register<ExtensionManifestBuilderTask>("updateManifest") {
    outputFile = File(buildDir, "distributions/manifest.json")
    val iconsResolutions = listOf(16, 32, 48, 128)
    val extensionName = when (System.getenv("TEST")?.toString()) {
        "true" -> "${Config.Name} Test"
        else -> Config.Name
    }
    manifest = Manifest(
        name = extensionName,
        description = "Extension for integrating streams into Twitch page.",
        permissions = listOf("storage", "tabs"),
        contentScripts = listOf(
            ContentScript(
                matches = listOf("*://*.twitch.tv/*"),
                js = listOf("content.js"),
            ),
        ),
        icons = iconsResolutions.associate { it.toString() to "icons/peepoGlad$it.png" },
        action = ManifestAction(
            icon = "icons/peepoGlad${iconsResolutions.last()}.png",
            popup = "index.html",
            title = extensionName,
        ),
    )
}

val buildWebpack = tasks.getByName("jsBrowserWebpack").apply {
    dependsOn(":content:jsBrowserWebpack", updateManifest)
    doLast {
        copy {
            from("content/build/distributions/content.js")
            into("build/distributions")
        }
    }
}

tasks.register<Zip>("zipExtension") {
    dependsOn(buildWebpack)
    archiveFileName.set("${project.name}-${Config.Version}.zip")
    from(layout.buildDirectory.dir("distributions"))
    destinationDirectory.set(layout.buildDirectory)
}
