package ru.kodzzzima.kodzzzimaweb.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kodzzzimaweb.composeapp.generated.resources.Res
import kodzzzimaweb.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import ru.kodzzzima.kodzzzimaweb.typewriter.TypewriterText

@Composable
fun BottomBar() {
    Row(
        verticalAlignment = Alignment.Companion.CenterVertically
    ) {
        Image(
            modifier = Modifier.Companion.size(36.dp).padding(4.dp),
            painter = painterResource(Res.drawable.compose_multiplatform),
            contentDescription = null
        )
        TypewriterText("Powered by Compose Multiplatform!")
    }
}