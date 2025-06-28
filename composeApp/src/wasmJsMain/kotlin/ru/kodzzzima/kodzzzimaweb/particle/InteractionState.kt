package ru.kodzzzima.kodzzzimaweb.particle

import androidx.compose.ui.geometry.Offset

data class InteractionState(
    val position: Offset = Offset.Companion.Zero,
    val isActive: Boolean = false
)