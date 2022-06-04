package tasks.manifest

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import java.io.File

abstract class ExtensionManifestBuilderTask : DefaultTask() {

    @get:Input
    abstract val outputFile: Property<File>

    private val json by lazy {
        Json {
            prettyPrint = true
            encodeDefaults = true
        }
    }

    @TaskAction
    fun build() {
        val iconsResolutions = listOf(16, 32, 48, 128)
        val manifest = Manifest(
            name = "Twitch Kino",
            description = "Extension for integrating streams into Twitch page.",
            permissions = listOf(
                "storage",
            ),
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
                title = "Twitch Kino",
            ),
        )
        val manifestJson = json.encodeToString(manifest)
        outputFile.get().writeText(manifestJson)
    }
}
