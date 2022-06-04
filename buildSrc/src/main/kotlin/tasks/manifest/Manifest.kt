package tasks.manifest

import Config
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Manifest(
    val name: String,
    val version: String = Config.Version,
    val description: String,
    @SerialName("manifest_version")
    val manifestVersion: Int = 3,
    val permissions: List<String>,
    val icons: Map<String, String>,
    val action: ManifestAction,
    @SerialName("content_scripts")
    val contentScripts: List<ContentScript>,
)
