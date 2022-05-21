import kotlinx.browser.document
import org.w3c.dom.HTMLIFrameElement
import org.w3c.dom.HTMLVideoElement

private fun hideTwitchPlayer() {
    val videoTags = document.getElementsByTagName("video")
    videoTags.forEach { (it as? HTMLVideoElement)?.pause() }
    val twitchPlayerContainer = document.getElementsByClassName("video-player__container--resize-calc")
        .firstOrNull() ?: return
    twitchPlayerContainer.children.forEach {
        it.style.display = "none"
    }
}

private fun createIframe(src: String): HTMLIFrameElement? {
    val frame = document.createElement("iframe") as? HTMLIFrameElement ?: return null
    frame.id = "twitch-kino-iframe"
    frame.src = src
    frame.allowFullscreen = true
    frame.classList.add("video-ref")
    return frame
}

private fun removeIframe() {
    val iframe = document.getElementById("twitch-kino-iframe") ?: return
    iframe.parentNode?.removeChild(iframe)
}

fun setupPlayer(url: String) {
    val frame = createIframe(url) ?: return
    val twitchPlayerContainer = document.getElementsByClassName("video-player__container--resize-calc")
        .firstOrNull() ?: return
    removeIframe()
    hideTwitchPlayer()
    twitchPlayerContainer.appendChild(frame)
}

fun restoreTwitchPlayer() {
    val twitchPlayerContainer = document.getElementsByClassName("video-player__container--resize-calc")
        .firstOrNull() ?: return
    removeIframe()
    twitchPlayerContainer.children.forEach {
        it.style.display = "block"
    }
}
