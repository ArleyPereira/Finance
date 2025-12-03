package br.com.hellodev.design.presenter.components.divider

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily

@Composable
fun HorizontalDividerWithText(
    modifier: Modifier = Modifier,
    text: String,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier
                .weight(1f),
            color = ColorScheme.colorScheme.divider.color
        )

        Text(
            text = text,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 10.dp),
            style = TextStyle(
                fontSize = 18.sp,
                lineHeight = 25.2.sp,
                fontFamily = UrbanistFamily,
                fontWeight = FontWeight.SemiBold,
                color = ColorScheme.colorScheme.text.primaryColor,
                letterSpacing = 0.2.sp,
            )
        )

        HorizontalDivider(
            modifier = Modifier
                .weight(1f),
            color = ColorScheme.colorScheme.divider.color
        )
    }
}

@PreviewLightDark
@Composable
private fun HorizontalDividerWithTextPreview() {
    HelloTheme {
        Column(
            modifier = Modifier
                .background(ColorScheme.colorScheme.screen.backgroundPrimary)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalDividerWithText(
                text = "ou"
            )
        }
    }
}