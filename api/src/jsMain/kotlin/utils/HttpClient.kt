package utils

import data.GoodGameStream
import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.serialization.json.Json

object HttpClient {

    private val json = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
    }

    suspend fun getGoodGameUserId(username: String): Result<Int> {
        return try {
            val response = window.fetch("https://api2.goodgame.ru/streams/$username")
                .await()
                .text()
                .await()
                .let { json.decodeFromString(GoodGameStream.serializer(), it) }
            Result.success(response.channel.id)
        } catch (e: Exception) {
            console.error(e)
            Result.failure(e)
        }
    }
}
