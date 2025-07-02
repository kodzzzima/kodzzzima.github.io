package ru.kodzzzima.kodzzzimaweb.image

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ClickableImage(
    painter: Painter,
    size: Dp = 48.dp,
    onClick: () -> Unit,
) {
    var isHovered by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val scaleAnim by animateFloatAsState(
        targetValue = if (isPressed) 1.2f else if (isHovered) 1.1f else 1f,
    )

    Image(
        painter = painter,
        contentDescription = null,
        modifier = Modifier.Companion.size(size)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
            .graphicsLayer {
                scaleX = scaleAnim
                scaleY = scaleAnim
            }
            .onPointerEvent(PointerEventType.Companion.Enter) { isHovered = true }
            .onPointerEvent(PointerEventType.Companion.Exit) { isHovered = false },

        colorFilter = ColorFilter.Companion.tint(Color(0xFF301B70)),
    )
}