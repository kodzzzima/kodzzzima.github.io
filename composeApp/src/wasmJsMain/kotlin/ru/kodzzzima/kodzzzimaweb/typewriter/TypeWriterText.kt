package ru.kodzzzima.kodzzzimaweb.typewriter

import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kodzzzimaweb.composeapp.generated.resources.Res
import kodzzzimaweb.composeapp.generated.resources.minecraft
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.Font

@Composable
fun TypewriterText(
    text: String,
    typingSpeed: Long = 100L,
    deletingSpeed: Long = 50L,
    pauseDuration: Long = 2000L
) {
    var displayText by remember { mutableStateOf("") }
    var isDeleting by remember { mutableStateOf(false) }
    var index by remember { mutableStateOf(0) }
    var showCursor by remember { mutableStateOf(true) }

    LaunchedEffect(text) {
        while (true) {
            if (!isDeleting) {
                if (index < text.length) {
                    displayText += text[index]
                    index++
                    delay(typingSpeed)
                } else {
                    delay(pauseDuration)
                    isDeleting = true
                }
            } else {
                if (index > 0) {
                    index--
                    displayText = displayText.dropLast(1)
                    delay(deletingSpeed)
                } else {
                    isDeleting = false
                    delay(pauseDuration)
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        while (true) {
            showCursor = !showCursor
            delay(500)
        }
    }

    Text(
        text = displayText + if (showCursor) "|" else " ",
        fontSize = 14.sp,
        fontFamily = FontFamily(
            Font(Res.font.minecraft, FontWeight.Normal)
        ),
        color = Color(0xFF555555)
    )
}