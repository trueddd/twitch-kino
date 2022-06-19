package ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

@Composable
fun Button(
    text: String,
    onClick: () -> Unit,
) {
    val hovered = remember(text) { mutableStateOf(false) }
    Div(
        attrs = {
            this.onClick { onClick() }
            style {
                cursor("pointer")
                border {
                    style = LineStyle.Solid
                    color = Colors.Accent
                    width = 1.px
                }
                padding(4.px)
                backgroundColor(if (hovered.value) Colors.Primary else Colors.Background)
                borderRadius(4.px)
                fontSize(14.px)
                color(if (hovered.value) Color.white else Colors.Accent)
            }
            onMouseOver { hovered.value = true }
            onMouseLeave { hovered.value = false }
        }
    ) {
        Text(text)
    }
}
