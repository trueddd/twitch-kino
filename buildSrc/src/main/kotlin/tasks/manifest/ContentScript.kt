package tasks.manifest

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContentScript(
    val matches: List<String>,
    val js: List<String>,
    @SerialName("all_frames")
    val allFrames: Boolean = true,
    val runAt: String = "document_start",
)
