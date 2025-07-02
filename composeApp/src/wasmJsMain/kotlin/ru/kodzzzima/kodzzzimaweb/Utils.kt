package ru.kodzzzima.kodzzzimaweb

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import kodzzzimaweb.composeapp.generated.resources.Res
import kodzzzimaweb.composeapp.generated.resources.bubble_sans
import kotlinx.browser.window
import org.jetbrains.compose.resources.Font

internal val bubbleSans: FontFamily
    @Composable
    get() = FontFamily(Font(Res.font.bubble_sans))

fun openLink(url: String) {
    window.open(url, "_blank")
}
