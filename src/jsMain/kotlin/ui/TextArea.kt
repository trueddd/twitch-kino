package ui

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Input

@Composable
fun TextArea(
    value: String,
    onValueChanged: (String) -> Unit,
) {
    Input(
        type = InputType.Text,
        attrs = {
            style {
                property("resize", "none")
                fontSize(14.px)
                margin(0.px, 10.px)
                backgroundColor(Color.white)
                borderRadius(4.px)
                borderWidth(0.px)
                padding(6.px)
            }
            value(value)
            onInput {
                onValueChanged(it.value)
            }
        }
    )
}
