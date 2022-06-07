import chrome.runtime.playerEventsFlow
import data.Player
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@OptIn(DelicateCoroutinesApi::class)
fun main() {
    chrome.runtime.onMessage.playerEventsFlow()
        .onEach { player ->
            when (player) {
                is Player.Twitch -> restoreTwitchPlayer()
                else -> setupPlayer(player.provideIframeLink())
            }
        }
        .launchIn(GlobalScope)
}
