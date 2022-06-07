package data

import utils.HttpClient

sealed class Player(
    open val username: String,
    val type: String,
) {

    companion object {
        const val DELIMITER = "|"
        object Type {
            const val Wasd = "wasd"
            const val GoodGame = "goodgame"
            const val Twitch = "twitch"
        }
    }

    abstract suspend fun provideIframeLink(): String

    data class Wasd(override val username: String) : Player(username, Type.Wasd) {

        override suspend fun provideIframeLink() = "https://wasd.tv/embed/${username}"

        override fun toString() = "$type$DELIMITER$username"
    }

    data class GoodGame(override val username: String) : Player(username, Type.GoodGame) {

        override suspend fun provideIframeLink(): String {
            val id = HttpClient.getGoodGameUserId(username).getOrThrow().toString()
            return "https://goodgame.ru/player?$id"
        }

        override fun toString() = "$type$DELIMITER$username"
    }

    object Twitch : Player("", Type.Twitch) {

        override suspend fun provideIframeLink() = ""

        override fun toString() = type
    }
}