package ru.kodzzzima.kodzzzimaweb.particle

data class ParticleConfig(
    val minSize: Float = 2f,
    val maxSize: Float = 5f,
    val minAlpha: Float = 0.3f,
    val maxAlpha: Float = 0.7f,
    val minSpeed: Float = 0.1f,
    val maxSpeed: Float = 0.5f,
    val cursorRadius: Float = 50f,
    val repelForce: Float = 5f,
)