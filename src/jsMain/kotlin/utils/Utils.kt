package utils

import chrome.runtime.TYPE_FIELD
import chrome.runtime.USERNAME_FIELD
import chrome.tabs.query
import chrome.tabs.sendMessage
import data.Player
import kotlin.js.json

fun changePlayer(player: Player) {
    query { active = true }
        .then { it.first() }
        .then { sendMessage(it.id, json(TYPE_FIELD to player.type.toString(), USERNAME_FIELD to player.username)) }
        .catch { console.error(it) }
}
