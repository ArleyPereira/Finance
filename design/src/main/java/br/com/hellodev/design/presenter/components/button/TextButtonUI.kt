package br.com.hellodev.design.presenter.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.core.enums.illustration.IllustrationType
import br.com.hellodev.design.extensions.modifier.shimmerPlaceholder
import br.com.hellodev.design.presenter.components.divider.HorizontalDividerUI
import br.com.hellodev.design.presenter.components.icon.default.getDrawableIllustration
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily

@Composable
fun TextButtonUI(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = ColorScheme.colorScheme.button.secondaryText,
    icon: IllustrationType? = null,
    iconTint: Color = ColorScheme.colorScheme.icon.default,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled && !isLoading,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            contentColor = textColor
        ),
        content = {
            Row(
                modifier = Modifier
                    .shimmerPlaceholder(visible = isLoading),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = text,
                    style = TextStyle(
                        lineHeight = 22.4.sp,
                        fontFamily = UrbanistFamily,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.2.sp
                    )
                )

                icon?.let {
                    Icon(
                        painter = getDrawableIllustration(type = it),
                        contentDescription = null,
                        tint = iconTint
                    )
                }
            }
        }
    )
}

@PreviewLightDark
@Composable
private fun TextButtonUIPreview() {
    HelloTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(ColorScheme.colorScheme.screen.backgroundPrimary),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextButtonUI(
                text = "Adicionar cupom",
                enabled = true,
                onClick = {}
            )

            HorizontalDividerUI()

            TextButtonUI(
                text = "Adicionar cupom",
                icon = IllustrationType.IC_EMAIL_FILL,
                iconTint = ColorScheme.colorScheme.defaultColor,
                enabled = true,
                onClick = {}
            )
        }
    }
}