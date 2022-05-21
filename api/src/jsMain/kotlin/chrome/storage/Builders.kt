package chrome.storage

import data.Player
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlin.js.json

private const val PLAYER_FIELD = "player"
private const val STORAGE_NEW_VALUE_FIELD = "newValue"

fun StorageArea.setPlayer(player: Player) {
    set(json(PLAYER_FIELD to player.toString()))
}

fun StorageArea.playerChangedFlow(): Flow<Player> {
    return callbackFlow {
        onChanged.addListener {
            val newPlayer = try { it[PLAYER_FIELD][STORAGE_NEW_VALUE_FIELD] as String } catch (e: Exception) { return@addListener }
            val newPlayerData = newPlayer.split(Player.DELIMITER, limit = 2)
            val player = when (newPlayerData.first()) {
                Player.Companion.Type.Wasd -> Player.Wasd(newPlayerData.last())
                Player.Companion.Type.GoodGame -> Player.GoodGame(newPlayerData.last())
                Player.Companion.Type.Twitch -> Player.Twitch
                else -> return@addListener
            }
            trySend(player)
        }
        awaitClose()
    }
}
