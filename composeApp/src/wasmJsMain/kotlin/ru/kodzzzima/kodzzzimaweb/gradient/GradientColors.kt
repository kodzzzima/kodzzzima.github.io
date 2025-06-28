package ru.kodzzzima.kodzzzimaweb.gradient

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val gradientColors = Brush.linearGradient(
    colors = listOf(
        Color(0xFFE3F2FD),  // Ледяной голубой
        Color(0xFFF3E5F5),  // Перламутрово-розовый
        Color(0xFFEDE7F6),  // Светло-сиреневый
        Color(0xFFD1C4E9),  // Перламутровый
        Color(0xFFB39DDB)   // Фиолетовый перламутр
    ),
    start = Offset(0f, 0f),
    end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
)