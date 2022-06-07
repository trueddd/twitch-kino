package chrome.runtime

import data.Player
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.mapNotNull
import kotlin.js.Json

const val TYPE_FIELD = "player"
const val USERNAME_FIELD = "username"

@Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
fun OnMessageEvents.messagesFlow(): Flow<Json> {
    return callbackFlow {
        addListener {
            val data = it as? Json ?: return@addListener
            trySend(data)
        }
        awaitClose()
    }
}

fun OnMessageEvents.playerEventsFlow(): Flow<Player> {
    return messagesFlow()
        .mapNotNull {
            val playerType = it[TYPE_FIELD] as? String ?: return@mapNotNull null
            val username = it[USERNAME_FIELD] as? String ?: ""
            playerType to username
        }
        .mapNotNull { (player, username) ->
            when (player) {
                Player.Companion.Type.Wasd -> Player.Wasd(username)
                Player.Companion.Type.GoodGame -> Player.GoodGame(username)
                Player.Companion.Type.Twitch -> Player.Twitch
                else -> null
            }
        }
}
