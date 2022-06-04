import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import chrome.storage.setPlayer
import data.Player
import kotlinx.coroutines.await
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable
import kotlin.js.json

@Composable
fun Popup() {
    val wasdChannel = remember { mutableStateOf("") }
    val goodGameChannel = remember { mutableStateOf("") }
    LaunchedEffect(Unit) {
        val storage = chrome.storage.sync.get(null).await()
        (storage[Player.Companion.Type.Wasd] as? String)?.let { wasdChannel.value = it }
        (storage[Player.Companion.Type.GoodGame] as? String)?.let { goodGameChannel.value = it }
    }
    Div(
        attrs = {
            style {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Column)
            }
        }
    ) {
        Div(
            attrs = {
                style {
                    display(DisplayStyle.Flex)
                    flexDirection(FlexDirection.Row)
                    alignItems(AlignItems.Center)
                }
            }
        ) {
            P {
                Text("WASD")
            }
            TextArea(
                value = wasdChannel.value,
                attrs = {
                    style {
                        property("resize", "none")
                        height(20.px)
                        marginLeft(10.px)
                        marginRight(10.px)
                    }
                    onInput {
                        console.log("wasd changed: ${it.value}")
                        wasdChannel.value = it.value
                        chrome.storage.sync.set(json(Player.Companion.Type.Wasd to it.value))
                    }
                }
            )
            Button(
                attrs = {
                    style {
                        whiteSpace("nowrap")
                        textAlign("center")
                    }
                    onClick {
                        chrome.storage.sync.setPlayer(Player.Wasd(wasdChannel.value))
                    }
                }
            ) {
                Text("Change player")
            }
        }
        Div(
            attrs = {
                style {
                    display(DisplayStyle.Flex)
                    flexDirection(FlexDirection.Row)
                    alignItems(AlignItems.Center)
                }
            }
        ) {
            P {
                Text("GG")
            }
            TextArea(
                value = goodGameChannel.value,
                attrs = {
                    style {
                        property("resize", "none")
                        height(20.px)
                        marginLeft(10.px)
                        marginRight(10.px)
                    }
                    onInput {
                        goodGameChannel.value = it.value
                        chrome.storage.sync.set(json(Player.Companion.Type.GoodGame to it.value))
                    }
                }
            )
            Button(
                attrs = {
                    style {
                        whiteSpace("nowrap")
                        textAlign("center")
                    }
                    onClick {
                        chrome.storage.sync.setPlayer(Player.GoodGame(goodGameChannel.value))
                    }
                }
            ) {
                Text("Change player")
            }
        }
        Div(
            attrs = {
                style {
                    display(DisplayStyle.Flex)
                    flexDirection(FlexDirection.Row)
                }
            }
        ) {
            Button(
                attrs = {
                    onClick { chrome.storage.sync.setPlayer(Player.Twitch) }
                }
            ) {
                Text("Restore Twitch player")
            }
        }
    }
}

fun main() {
    renderComposable(rootElementId = "root") {
        Popup()
    }
}
