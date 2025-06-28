package ru.kodzzzima.kodzzzimaweb.particle

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import kotlin.math.sin
import kotlin.random.Random

class Particle private constructor(
    private val config: ParticleConfig
) {
    private var position = Offset.Zero
    private var velocity = Offset.Zero
    private var targetOffset = Offset.Zero
    private val size = Random.nextFloat() * (config.maxSize - config.minSize) + config.minSize
    private val baseAlpha = Random.nextFloat() * (config.maxAlpha - config.minAlpha) + config.minAlpha
    private val speed = Random.nextFloat() * (config.maxSpeed - config.minSpeed) + config.minSpeed

    companion object {
        fun create(config: ParticleConfig, bounds: Size): Particle {
            return Particle(config).apply {
                reset(bounds)
            }
        }
    }
    private fun reset(bounds: Size) {
        position = Offset(
            Random.nextFloat() * bounds.width,
            Random.nextFloat() * bounds.height
        )
        velocity = Offset.Zero
        targetOffset = Offset(
            (Random.nextFloat() * 2 - 1) * 200f,
            (Random.nextFloat() * 2 - 1) * 200f
        )
    }

    fun update(time: Float, bounds: Size, interactionState: InteractionState) {
        // Эффект курсора — лёгкая пульсация
        if (interactionState.isActive) {
            val distance = (position - interactionState.position).getDistance()
            if (distance < config.cursorRadius) {
                val repelDir = (position - interactionState.position).normalized()
                val force = (config.cursorRadius - distance) / config.cursorRadius * config.repelForce
                val wobble = sin(time * 3 + distance * 0.05f) * 0.5f
                velocity += repelDir * (force + wobble)
            }
        }

        // Случайный "хаос"
        val chaos = Offset(
            (Random.nextFloat() - 0.5f) * 0.1f,
            (Random.nextFloat() - 0.5f) * 0.1f
        )
        velocity += chaos

        // Периодическая смена направления
        if (Random.nextFloat() < 0.005f) {
            val randomDirection = Offset(
                (Random.nextFloat() * 2 - 1),
                (Random.nextFloat() * 2 - 1)
            ).normalized()
            velocity += randomDirection * speed * 0.3f
        }

        velocity *= 0.99f // замедление
        position += velocity

        // Обработка краёв
        if (position.x < 0f) position = position.copy(x = bounds.width)
        if (position.x > bounds.width) position = position.copy(x = 0f)
        if (position.y < 0f) position = position.copy(y = bounds.height)
        if (position.y > bounds.height) position = position.copy(y = 0f)
    }



    private fun randomizeTarget() {
        targetOffset = Offset(
            (Random.nextFloat() * 2 - 1) * 200f,
            (Random.nextFloat() * 2 - 1) * 200f
        )
    }

    fun draw(scope: DrawScope) {
        val alphaMod = 0.9f + 0.1f * sin(position.x * 0.01f + position.y * 0.01f)
        scope.drawCircle(
            color = Color.White.copy(alpha = baseAlpha * alphaMod),
            radius = size,
            center = position
        )
    }

    private fun Offset.normalized(): Offset {
        val dist = getDistance()
        return if (dist > 0f) this / dist else Offset.Zero
    }
}


