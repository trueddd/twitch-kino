package ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import changePlayer
import chrome.storage.getExtensionStorage
import chrome.storage.savePlayer
import chrome.storage.sync
import data.Player
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

@Composable
fun Popup() {
    val wasdChannel = remember { mutableStateOf("") }
    val goodGameChannel = remember { mutableStateOf("") }
    LaunchedEffect(Unit) {
        val storage = sync.getExtensionStorage()
        wasdChannel.value = storage.wasdChannel ?: ""
        goodGameChannel.value = storage.goodGameChannel ?: ""
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
                        sync.savePlayer(Player.Wasd(it.value))
                    }
                }
            )
            Button(
                attrs = {
                    style {
                        whiteSpace("nowrap")
                        textAlign("center")
                    }
                    onClick { changePlayer(Player.Wasd(wasdChannel.value)) }
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
                        sync.savePlayer(Player.GoodGame(it.value))
                    }
                }
            )
            Button(
                attrs = {
                    style {
                        whiteSpace("nowrap")
                        textAlign("center")
                    }
                    onClick { changePlayer(Player.GoodGame(goodGameChannel.value)) }
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
                    onClick { changePlayer(Player.Twitch) }
                }
            ) {
                Text("Restore Twitch player")
            }
        }
    }
}
