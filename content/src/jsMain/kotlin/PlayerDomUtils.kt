import kotlinx.browser.document
import org.w3c.dom.HTMLIFrameElement
import org.w3c.dom.HTMLVideoElement

fun hideTwitchPlayer() {
    val videoTags = document.getElementsByTagName("video")
    videoTags.forEach { (it as? HTMLVideoElement)?.pause() }
    val twitchPlayerContainer = document.getElementsByClassName("video-player__container--resize-calc")
        .firstOrNull() ?: return
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
    val twitchPlayerContainer = document.getElementsByClassName("video-player__container--resize-calc")
        .firstOrNull() ?: return
    hideTwitchPlayer()
    twitchPlayerContainer.appendChild(frame)
}

fun restoreTwitchPlayer() {
    val twitchPlayerContainer = document.getElementsByClassName("video-player__container--resize-calc")
        .firstOrNull() ?: return
    val iframe = twitchPlayerContainer.getElementsByTagName("iframe").firstOrNull() ?: return
    twitchPlayerContainer.removeChild(iframe)
    twitchPlayerContainer.children.forEach {
        it.style.display = "block"
    }
}
