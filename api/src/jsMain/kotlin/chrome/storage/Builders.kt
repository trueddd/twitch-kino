package chrome.storage

import data.Player
import kotlinx.coroutines.await
import kotlin.js.json

fun StorageArea.savePlayer(player: Player) {
    if (player is Player.Twitch) {
        return
    }
    set(json(player.type to player.username))
}

suspend fun StorageArea.getExtensionStorage(): ExtensionStorage {
    val storage = get(null).await()
    val wasdChannel = storage[Player.Companion.Type.Wasd] as? String
    val goodGameChannel = storage[Player.Companion.Type.GoodGame] as? String
    return ExtensionStorage(wasdChannel, goodGameChannel)
}
