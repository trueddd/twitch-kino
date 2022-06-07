package tasks.manifest

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ManifestAction(
    @SerialName("default_icon")
    val icon: String,
    @SerialName("default_popup")
    val popup: String,
    @SerialName("default_title")
    val title: String,
)
