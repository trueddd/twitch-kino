package data

import kotlinx.serialization.Serializable

@Serializable
data class GoodGameStream(
    val channel: GoodGameChannel,
)
