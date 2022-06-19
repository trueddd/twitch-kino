package utils

import data.GoodGameChannel
import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json

object HttpClient {

    private val json = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
    }

    suspend fun getGoodGameUserId(username: String): Result<Int> {
        return try {
            val response = window.fetch("https://goodgame.ru/api/getchannelstatus?id=$username&fmt=json")
                .await()
                .text()
                .await()
                .let {
                    val serializer = MapSerializer(String.serializer(), GoodGameChannel.serializer())
                    json.decodeFromString(serializer, it)
                }
            Result.success(response.values.first().id)
        } catch (e: Exception) {
            console.error(e)
            Result.failure(e)
        }
    }
}
