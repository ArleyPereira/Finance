package br.com.hellodev.design.presenter.components.textfield.click

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.core.enums.illustration.IllustrationType
import br.com.hellodev.design.presenter.components.icon.default.getDrawableIllustration
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily

@Composable
fun TextFieldClickUI(
    modifier: Modifier = Modifier,
    value: String = "",
    label: String,
    painter: Painter? = null,
    isError: Boolean = false,
    enabled: Boolean = true,
    error: String = "",
    onClick: () -> Unit
) {
    val borderColor = if (isError) {
        ColorScheme.colorScheme.alertColor
    } else {
        ColorScheme.colorScheme.border.unselected
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                enabled = enabled,
                onClick = onClick
            )
    ) {
        Row(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth()
                .background(
                    color = if (isError) {
                        ColorScheme.colorScheme.alertAlphaColor
                    } else ColorScheme.colorScheme.textField.background,
                    shape = RoundedCornerShape(12.dp)
                )
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                if (label.isNotEmpty()) {
                    Text(
                        fontFamily = UrbanistFamily,
                        text = label,
                        style = TextStyle(
                            fontSize = 12.sp,
                            lineHeight = 15.4.sp,
                            fontFamily = UrbanistFamily,
                            color = ColorScheme.colorScheme.textField.placeholder
                        )
                    )
                }

                if (value.isNotEmpty()) {
                    Text(
                        text = value,
                        style = TextStyle(
                            lineHeight = 22.4.sp,
                            fontFamily = UrbanistFamily,
                            color = if (enabled) {
                                ColorScheme.colorScheme.textField.text
                            } else ColorScheme.colorScheme.textField.disabledText
                        )
                    )
                }
            }

            painter?.let {
                Icon(
                    painter = it,
                    contentDescription = null,
                    tint = ColorScheme.colorScheme.icon.color
                )
            }
        }

        if (isError) {
            Row(
                modifier = Modifier
                    .padding(start = 16.dp, top = 6.dp)
            ) {
                Icon(
                    painter = getDrawableIllustration(type = IllustrationType.IC_ALERT),
                    contentDescription = null,
                    tint = ColorScheme.colorScheme.alertColor
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = error,
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 19.6.sp,
                        fontFamily = UrbanistFamily,
                        color = ColorScheme.colorScheme.alertColor,
                        letterSpacing = 0.2.sp
                    )
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun TextFieldClickPreview() {
    HelloTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(ColorScheme.colorScheme.screen.backgroundPrimary),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextFieldClickUI(
                modifier = Modifier
                    .padding(32.dp),
                value = "Masculino",
                label = "Gênero",
                painter = getDrawableIllustration(type = IllustrationType.IC_ARROW_RIGHT),
                isError = false,
                onClick = {}
            )
        }
    }
}