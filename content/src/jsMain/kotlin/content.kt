import chrome.storage.playerChangedFlow
import chrome.storage.setPlayer
import data.Player
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

@OptIn(DelicateCoroutinesApi::class)
fun main(vararg args: String) {
    with(chrome.storage.sync) {
        playerChangedFlow()
            .onStart { setPlayer(Player.Twitch) }
            .onEach { player ->
                console.log("Player change confirm: $player")
                val url = player.provideIframeLink()
                when (player) {
                    is Player.Twitch -> restoreTwitchPlayer()
                    else -> setupPlayer(url)
                }
            }
            .launchIn(GlobalScope)
    }
}
