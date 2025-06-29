package ru.kodzzzima.kodzzzimaweb

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kodzzzimaweb.composeapp.generated.resources.Res
import kodzzzimaweb.composeapp.generated.resources.bubble_sans
import kodzzzimaweb.composeapp.generated.resources.compose_multiplatform
import kodzzzimaweb.composeapp.generated.resources.github_mark
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import ru.kodzzzima.kodzzzimaweb.gradient.gradientColors
import ru.kodzzzima.kodzzzimaweb.particle.InteractiveParticleEffect
import ru.kodzzzima.kodzzzimaweb.typewriter.TypewriterText

@Composable
fun App() {
    MaterialTheme {
        Scaffold(
            bottomBar = {
                BottomBar()
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(brush = gradientColors),
                contentAlignment = Alignment.Center
            ) {
                InteractiveParticleEffect()
                MainText(Modifier.padding(paddingValues = paddingValues))
            }
        }
    }
}

@Composable
private fun BottomBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(36.dp).padding(4.dp),
            painter = painterResource(Res.drawable.compose_multiplatform),
            contentDescription = null
        )
        TypewriterText("Powered by Compose Multiplatform!")
    }
}

@Composable
private fun MainText(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = " Hi There! I’m Dmitrii Kostyk",
            fontSize = 24.sp,
            fontFamily = bubbleSans,
            textAlign = TextAlign.Center,
            color = Color(0xFF4527A0)
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = "Mobile Developer",
            fontSize = 20.sp,
            fontFamily = bubbleSans,
            textAlign = TextAlign.Center,
            color = Color(0xFF4527A0)
        )
        // TODO click
        Image(
            painter = painterResource(Res.drawable.github_mark),
            contentDescription = null,
            modifier = Modifier.size(48.dp)
        )
    }
}

internal val bubbleSans: FontFamily
    @Composable
    get() = FontFamily(
        Font(Res.font.bubble_sans, FontWeight.Normal)
    )