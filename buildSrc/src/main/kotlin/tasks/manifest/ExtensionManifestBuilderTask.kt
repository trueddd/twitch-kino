package tasks.manifest

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import java.io.File

abstract class ExtensionManifestBuilderTask : DefaultTask() {

    @get:Input
    abstract var manifest: Manifest

    @get:Input
    abstract var outputFilePath: String

    private val json by lazy {
        Json {
            prettyPrint = true
            encodeDefaults = true
            explicitNulls = false
        }
    }

    @TaskAction
    fun build() {
        val manifestJson = json.encodeToString(manifest)
        File(outputFilePath)
            .also {
                if (!it.exists()) {
                    it.parentFile.mkdirs()
                    it.createNewFile()
                }
            }
            .writeText(manifestJson)
    }
}
