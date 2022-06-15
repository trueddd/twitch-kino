import chrome.runtime.TYPE_FIELD
import chrome.runtime.USERNAME_FIELD
import chrome.tabs.query
import chrome.tabs.sendMessage
import data.Player
import org.jetbrains.compose.web.renderComposable
import ui.Popup
import kotlin.js.json

fun changePlayer(player: Player) {
    query { active = true }
        .then { it.first() }
        .then { sendMessage(it.id, json(TYPE_FIELD to player.type, USERNAME_FIELD to player.username)) }
        .catch { console.error(it) }
}

fun main() {
    renderComposable(rootElementId = "root") {
        Popup()
    }
}
