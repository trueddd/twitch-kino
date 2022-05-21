package data

import kotlinx.serialization.Serializable

@Serializable
data class GoodGameChannel(
    val id: Int,
    val key: String,
)
