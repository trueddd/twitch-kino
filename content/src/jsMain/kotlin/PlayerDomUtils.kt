import kotlinx.browser.document
import org.w3c.dom.Element
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
    frame.src = src
    frame.allowFullscreen = true
    frame.classList.add("video-ref")
    return frame
}

private fun Element.removeIframe() {
    val iframe = getElementsByTagName("iframe").firstOrNull() ?: return
    removeChild(iframe)
}

fun setupPlayer(url: String) {
    val frame = createIframe(url) ?: return
    val twitchPlayerContainer = document.getElementsByClassName("video-player__container--resize-calc")
        .firstOrNull() ?: return
    twitchPlayerContainer.removeIframe()
    hideTwitchPlayer()
    twitchPlayerContainer.appendChild(frame)
}

fun restoreTwitchPlayer() {
    val twitchPlayerContainer = document.getElementsByClassName("video-player__container--resize-calc")
        .firstOrNull() ?: return
    twitchPlayerContainer.removeIframe()
    twitchPlayerContainer.children.forEach {
        it.style.display = "block"
    }
}
