package data

import utils.HttpClient

sealed class Player(
    open val username: String,
    val type: Type,
) {

    sealed class Type(val name: String) {

        override fun toString(): String {
            return name.lowercase()
        }

        object Wasd : Type("Wasd")
        object GoodGame : Type("GoodGame")
        object Twitch : Type("Twitch")
    }

    companion object {

        const val DELIMITER = "|"

        fun create(type: Type, username: String): Player {
            return when (type) {
                is Type.Wasd -> Wasd(username)
                is Type.GoodGame -> GoodGame(username)
                is Type.Twitch -> Twitch
            }
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

        override fun toString() = type.name
    }
}
