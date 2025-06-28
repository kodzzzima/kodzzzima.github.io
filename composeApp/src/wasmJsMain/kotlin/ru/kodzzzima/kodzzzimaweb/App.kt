package ru.kodzzzima.kodzzzimaweb

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kodzzzimaweb.composeapp.generated.resources.Res
import kodzzzimaweb.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import ru.kodzzzima.kodzzzimaweb.gradient.gradientColors
import ru.kodzzzima.kodzzzimaweb.particle.InteractiveParticleEffect
import ru.kodzzzima.kodzzzimaweb.typewriter.TypewriterText

@Composable
fun App() {
    MaterialTheme {
        Box(
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize()
                .background(brush = gradientColors),
            contentAlignment = Alignment.Center
        ) {
            InteractiveParticleEffect()
            MainText()
        }
    }
}

@Composable
private fun MainText() {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = " Hi There! I’m Dmitrii Kostyk",
            fontSize = 24.sp,
            fontFamily = FontFamily.Serif,
            color = Color(0xFF4527A0)
        )
        Row {
            Image(
                modifier = Modifier.size(24.dp),
                painter = painterResource(Res.drawable.compose_multiplatform),
                contentDescription = null
            )
            TypewriterText(
                "Powered by Compose Multiplatform"
            )
        }
    }
}