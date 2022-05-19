import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.renderComposable
import kotlin.js.json

fun main(args: Array<String>) {
    renderComposable(rootElementId = "root") {
        // main div
        Div({
            style {
                padding(25.px)
            }
        }) {

            Button(attrs = {
                onClick {
                    chrome.storage.sync.set(json("player" to "wasd:peacedaria"))
                }
            }) {
                Text("wasd")
            }
            Button(attrs = {
                onClick {
                    chrome.storage.sync.set(json("player" to "twitch"))
                }
            }) {
                Text("twitch")
            }
        }
    }
}
