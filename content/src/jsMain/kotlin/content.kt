import kotlinx.browser.document
import org.w3c.dom.*
import kotlin.js.json

fun HTMLCollection.forEach(block: (HTMLElement) -> Unit) {
    for (index in 0 until length) {
        (get(index) as? HTMLElement)?.let(block)
    }
}

fun hideTwitchPlayer() {
    val videoTags = document.getElementsByTagName("video")
    videoTags.forEach { (it as? HTMLVideoElement)?.pause() }
    val twitchPlayerContainer = document.getElementsByClassName("video-player__container--resize-calc")[0] ?: return
    twitchPlayerContainer.children.forEach {
        it.style.display = "none"
    }
}

fun createIframe(src: String): HTMLIFrameElement? {
    val frame = document.createElement("iframe") as? HTMLIFrameElement ?: return null
    frame.src = src
    frame.allowFullscreen = true
    frame.classList.add("video-ref")
    return frame
}

fun setupPlayer(url: String) {
    val frame = createIframe(url) ?: return
    val twitchPlayerContainer = document.getElementsByClassName("video-player__container--resize-calc")[0] ?: return
    hideTwitchPlayer()
    twitchPlayerContainer.appendChild(frame)
}

fun restoreTwitchPlayer() {
    val twitchPlayerContainer = document.getElementsByClassName("video-player__container--resize-calc")[0] ?: return
    val iframe = twitchPlayerContainer.getElementsByTagName("iframe")[0] ?: return
    twitchPlayerContainer.removeChild(iframe)
    twitchPlayerContainer.children.forEach {
        it.style.display = "block"
    }
}

fun main(vararg args: String) {
    console.log("content script started")
    chrome.storage.sync.set(json("player" to "twitch"))
    chrome.storage.sync.onChanged.addListener {
        console.log(it)
        val newPlayer = it["player"]["newValue"] as? String ?: return@addListener
        console.log(newPlayer)
        val newPlayerData = newPlayer.split(":", limit = 2)
        when (newPlayerData.first()) {
            "wasd" -> setupPlayer("https://wasd.tv/embed/${newPlayerData.last()}")
            "twitch" -> restoreTwitchPlayer()
        }
    }
}
