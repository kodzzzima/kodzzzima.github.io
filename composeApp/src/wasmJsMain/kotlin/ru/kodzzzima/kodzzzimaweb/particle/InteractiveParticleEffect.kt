package ru.kodzzzima.kodzzzimaweb.particle
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.toSize

@Composable
fun InteractiveParticleEffect(
    modifier: Modifier = Modifier,
    particleCount: Int = 10000,
    particleConfig: ParticleConfig = ParticleConfig()
) {
    var interactionState by remember { mutableStateOf(InteractionState()) }
    var canvasSize by remember { mutableStateOf(Size.Zero) }

    val particles = remember(particleCount, particleConfig, canvasSize) {
        if (canvasSize.width > 0 && canvasSize.height > 0) {
            List(particleCount) {
                Particle.create(
                    config = particleConfig,
                    bounds = canvasSize
                )
            }
        } else emptyList()
    }

    val time by rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = 100f,
        animationSpec = infiniteRepeatable(tween(10000, easing = LinearEasing))
    )

    val pointerModifier = modifier
        .fillMaxSize()
        .pointerInput(Unit) {
            awaitPointerEventScope {
                while (true) {
                    val event = awaitPointerEvent()
                    when (event.type) {
                        PointerEventType.Move -> interactionState = interactionState.copy(
                            position = event.changes.first().position,
                            isActive = true
                        )
                        PointerEventType.Release -> interactionState = interactionState.copy(isActive = false)
                        else -> {}
                    }
                }
            }
        }

    Box(pointerModifier) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .onSizeChanged { canvasSize = it.toSize() }
        ) {
            if (canvasSize == Size.Zero) canvasSize = this.size
            particles.forEach { particle ->
                particle.update(time, size, interactionState)
                particle.draw(this)
            }
        }
    }
}
