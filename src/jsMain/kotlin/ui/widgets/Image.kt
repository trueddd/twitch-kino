package ui.widgets

import androidx.compose.runtime.Composable
import data.Player
import org.jetbrains.compose.web.css.borderRadius
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Img

@Composable
fun PlatformIcon(
    playerType: Player.Type,
) {
    Img(
        src = "/$playerType.png",
        alt = playerType.name,
        attrs = {
            style {
                width(24.px)
                height(24.px)
                borderRadius(4.px)
            }
            title(playerType.name)
        }
    )
}
