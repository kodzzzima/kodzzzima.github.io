package ru.kodzzzima.kodzzzimaweb.animation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import kotlinx.coroutines.delay
import kotlin.math.*
import kotlin.random.Random

data class Particle(
    var position: Offset,
    val velocity: Offset,
    var alpha: Float
)

@Composable
fun CircleExplosion(
    modifier: Modifier = Modifier
) {
    var phase by remember { mutableStateOf("growing") }
    var radius by remember { mutableStateOf(0f) }
    var particles by remember { mutableStateOf(emptyList<Particle>()) }
    var canvasSize by remember { mutableStateOf(Size.Zero) }

    val maxRadius = 100f
    val particleCount = 10000

    LaunchedEffect(Unit) {
        // Фаза 1: круг надувается
        val steps = 40
        repeat(steps) {
            radius = (it / steps.toFloat()) * maxRadius
            delay(16)
        }

        // Фаза 2: сразу взрыв — генерируем частицы из всей площади круга
        val center = Offset(canvasSize.width / 2, canvasSize.height / 2)

        val particlesList = List(particleCount) {
            val angle = Random.nextDouble(0.0, 2 * PI)
            val speed = Random.nextDouble(5.0, 12.0)

            // --- Распределение позиции внутри круга ---
            val r = sqrt(Random.nextFloat().toDouble()) * radius // sqrt для равномерного распределения по площади
            val offsetX = cos(angle) * r
            val offsetY = sin(angle) * r
            val position = Offset(
                (center.x + offsetX).toFloat(),
                (center.y + offsetY).toFloat()
            )

            val velocity = Offset(
                (cos(angle) * speed).toFloat(),
                (sin(angle) * speed).toFloat()
            )

            Particle(position = position, velocity = velocity, alpha = 1f)
        }

        particles = particlesList
        phase = "exploding"

        // Фаза 3: движение частиц
        repeat(100) {
            particles = particles.map {
                it.copy(
                    position = it.position + it.velocity,
                    alpha = it.alpha - 0.01f
                )
            }.filter { it.alpha > 0f }
            delay(16)
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .onSizeChanged { canvasSize = it.toSize() }
        ) {
            if (phase == "growing") {
                drawCircle(
                    color = Color.White,
                    radius = radius,
                    center = center
                )
            } else if (phase == "exploding") {
                particles.forEach { p ->
                    drawCircle(
                        color = Color.White.copy(alpha = p.alpha),
                        radius = 3f,
                        center = p.position
                    )
                }
            }
        }
    }
}
