package br.com.hellodev.design.presenter.components.image

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.com.hellodev.core.functions.string.getInitials
import br.com.hellodev.design.functions.getColorFromInitials
import br.com.hellodev.design.presenter.components.spacer.VerticalSpacer
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily

@Composable
fun ImageFromInitials(
    modifier: Modifier = Modifier,
    firstName: String?,
    lastName: String?,
    onClick: () -> Unit
) {
    var boxSizePx by remember { mutableStateOf(0) }
    val density = LocalDensity.current

    Box(
        modifier = modifier
            .aspectRatio(1f)
            .background(
                color = getColorFromInitials(
                    firstName = firstName,
                    lastName = lastName
                ),
                shape = CircleShape
            )
            .clickable { onClick() }
            .clip(CircleShape)
            .onSizeChanged { size ->
                boxSizePx = size.width
            },
        contentAlignment = Alignment.Center
    ) {
        val fontSize = with(density) { (boxSizePx / 3).toSp() }

        Text(
            text = getInitials(
                firstName = firstName,
                lastName = lastName
            ),
            style = TextStyle(
                fontSize = fontSize,
                fontFamily = UrbanistFamily,
                fontWeight = FontWeight(700),
                color = Color.White
            )
        )
    }
}

@PreviewLightDark
@Composable
private fun ImageFromInitialsPreview() {
    HelloTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorScheme.colorScheme.screen.backgroundPrimary),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImageFromInitials(
                modifier = Modifier
                    .size(120.dp),
                firstName = "Arley",
                lastName = "Santana",
                onClick = {}
            )

            VerticalSpacer(size = 32)

            ImageFromInitials(
                modifier = Modifier
                    .size(60.dp),
                firstName = "Arley",
                lastName = "Santana",
                onClick = {}
            )
        }
    }
}