package data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GoodGameChannel(
    @SerialName("stream_id")
    val id: Int,
    val key: String,
)
