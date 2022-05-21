package data

import utils.HttpClient

sealed class Player(
    open val username: String,
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

    data class Wasd(override val username: String) : Player(username) {

        override suspend fun provideIframeLink() = "https://wasd.tv/embed/${username}"

        override fun toString() = "${Type.Wasd}$DELIMITER$username"
    }

    data class GoodGame(override val username: String) : Player(username) {

        override suspend fun provideIframeLink() = HttpClient.getGoodGameUserId(username).getOrThrow().toString()

        override fun toString() = "${Type.GoodGame}$DELIMITER$username"
    }

    object Twitch : Player("") {

        override suspend fun provideIframeLink() = ""

        override fun toString() = Type.Twitch
    }
}