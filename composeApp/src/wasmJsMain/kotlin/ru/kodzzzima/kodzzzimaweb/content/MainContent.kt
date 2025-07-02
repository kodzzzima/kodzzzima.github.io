package ru.kodzzzima.kodzzzimaweb.content

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kodzzzimaweb.composeapp.generated.resources.Res
import kodzzzimaweb.composeapp.generated.resources.github_mark
import kodzzzimaweb.composeapp.generated.resources.onlyfans
import kodzzzimaweb.composeapp.generated.resources.telegram_icon
import org.jetbrains.compose.resources.painterResource
import ru.kodzzzima.kodzzzimaweb.bubbleSans
import ru.kodzzzima.kodzzzimaweb.gradient.gradientColors
import ru.kodzzzima.kodzzzimaweb.image.ClickableImage
import ru.kodzzzima.kodzzzimaweb.openLink
import ru.kodzzzima.kodzzzimaweb.particle.InteractiveParticleEffect

@Composable
fun MainContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradientColors),
        contentAlignment = Alignment.Center
    ) {
        InteractiveParticleEffect()
        InfoSection()
    }
}


@Composable
private fun InfoSection() {
    var visible by remember { mutableStateOf(false) }

    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(durationMillis = 1000)
    )

    LaunchedEffect(Unit) {
        visible = true
    }

    Column(
        modifier = Modifier.graphicsLayer { this.alpha = alpha },
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextColumn()
        ImagesRow()
    }
}

@Composable
private fun TextColumn() {
    Text(
        text = " Hi There! I’m Dmitrii Kostyk",
        fontSize = 24.sp,
        fontFamily = bubbleSans,
        textAlign = TextAlign.Companion.Center,
        color = Color(0xFF4527A0)
    )
    Text(
        modifier = Modifier.Companion.padding(top = 8.dp),
        text = "Mobile Developer",
        fontSize = 20.sp,
        fontFamily = bubbleSans,
        textAlign = TextAlign.Companion.Center,
        color = Color(0xFF4527A0)
    )
}

@Composable
private fun ImagesRow() {
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ClickableImage(
            painterResource(Res.drawable.telegram_icon),
            onClick = { openLink("https://t.me/kodzzzima") }
        )
        ClickableImage(
            painterResource(Res.drawable.github_mark),
            onClick = { openLink("https://github.com/kodzzzima/") }
        )
        ClickableImage(
            painterResource(Res.drawable.onlyfans),
            size = 64.dp,
            onClick = { openLink("https://www.youtube.com/watch?v=dQw4w9WgXcQ&list=RDdQw4w9WgXcQ") }
        )
    }
}