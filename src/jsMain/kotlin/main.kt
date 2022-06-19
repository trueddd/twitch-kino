import kotlinx.browser.document
import org.jetbrains.compose.web.renderComposable
import ui.Popup

fun main() {
    document.body?.style?.margin = "0px"
    renderComposable(rootElementId = "root") {
        Popup()
    }
}
