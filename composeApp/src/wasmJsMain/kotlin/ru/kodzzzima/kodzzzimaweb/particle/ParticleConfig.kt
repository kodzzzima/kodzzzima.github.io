package ru.kodzzzima.kodzzzimaweb.particle

data class ParticleConfig(
    val minSize: Float = 2f,
    val maxSize: Float = 5f,
    val minAlpha: Float = 0.3f,
    val maxAlpha: Float = 0.6f,
    val minSpeed: Float = 0.1f,
    val maxSpeed: Float = 0.5f,
    val cursorRadius: Float = 70f,
    val repelForce: Float = 5f,
)