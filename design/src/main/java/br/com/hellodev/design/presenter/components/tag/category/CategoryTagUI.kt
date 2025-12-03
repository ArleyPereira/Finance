package br.com.hellodev.design.presenter.components.tag.category

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily

@Composable
fun CategoryTagUI(
    modifier: Modifier = Modifier,
    text: String,
    isSelect: Boolean = false,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelect) {
        ColorScheme.colorScheme.defaultColor
    } else {
        Color.Transparent
    }

    val textColor = if (isSelect) {
        Color.White
    } else {
        ColorScheme.colorScheme.defaultColor
    }

    Row(
        modifier = modifier
            .clip(shape = CircleShape)
            .background(backgroundColor)
            .border(
                width = 1.dp,
                color = ColorScheme.colorScheme.defaultColor,
                shape = CircleShape
            )
            .clickable { onClick() }
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(
                    vertical = 8.dp,
                    horizontal = 20.dp
                ),
            style = TextStyle(
                fontSize = 10.sp,
                fontFamily = UrbanistFamily,
                fontWeight = FontWeight(600),
                color = textColor,
                letterSpacing = 0.2.sp
            )
        )
    }
}

@PreviewLightDark
@Composable
private fun CategoryTagUIPreview() {
    HelloTheme {
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CategoryTagUI(
                text = "Freenlance",
                isSelect = true,
                onClick = {}
            )

            CategoryTagUI(
                text = "Remoto",
                isSelect = false,
                onClick = {}
            )
        }
    }
}