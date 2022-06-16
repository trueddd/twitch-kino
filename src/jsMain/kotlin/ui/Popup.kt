package ui

import androidx.compose.runtime.*
import chrome.storage.getExtensionStorage
import chrome.storage.savePlayer
import chrome.storage.sync
import data.Player
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.TextArea
import utils.changePlayer

@Composable
fun PlayerRow(
    playerType: Player.Type,
    channelState: MutableState<String>,
) {
    Div(
        attrs = {
            style {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Row)
                justifyContent(JustifyContent.FlexEnd)
                alignItems(AlignItems.Center)
            }
        }
    ) {
        Div(
            attrs = {
                style {
                    fontSize(16.px)
                    property("margin-right", "auto")
                }
            }
        ) {
            Text(playerType.name)
        }
        TextArea(
            value = channelState.value,
            attrs = {
                style {
                    property("resize", "none")
                    height(20.px)
                    marginLeft(10.px)
                    marginRight(10.px)
                }
                onInput {
                    console.log("$playerType changed: ${it.value}")
                    channelState.value = it.value
                    sync.savePlayer(Player.Wasd(it.value))
                }
            }
        )
        Button(
            text = "Change",
            onClick = { changePlayer(Player.create(playerType, channelState.value)) },
        )
    }
}

@Composable
fun TwitchRow() {
    Div(
        attrs = {
            style {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Row)
                justifyContent(JustifyContent.FlexEnd)
                alignItems(AlignItems.Center)
            }
        }
    ) {
        Button(
            text = "Restore Twitch player",
            onClick = { changePlayer(Player.Twitch) }
        )
    }
}

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
                backgroundColor(Colors.Background)
                gap(8.px)
                padding(8.px)
            }
        }
    ) {
        PlayerRow(Player.Type.Wasd, wasdChannel)
        PlayerRow(Player.Type.GoodGame, goodGameChannel)
        TwitchRow()
    }
}
