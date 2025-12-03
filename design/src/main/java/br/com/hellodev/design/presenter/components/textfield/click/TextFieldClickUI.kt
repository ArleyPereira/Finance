package br.com.hellodev.design.presenter.components.textfield.click

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.core.enums.illustration.IllustrationType
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_RIGHT
import br.com.hellodev.design.presenter.components.icon.default.DefaultIcon
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily

@Composable
fun TextFieldClickUI(
    modifier: Modifier = Modifier,
    value: String = "",
    label: String,
    illustrationType: IllustrationType,
    isError: Boolean = false,
    enabled: Boolean = true,
    error: String = "",
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                enabled = enabled,
                onClick = onClick
            )
    ) {
        Text(
            text = label,
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 22.4.sp,
                fontFamily = UrbanistFamily,
                fontWeight = FontWeight(500),
                color = ColorScheme.colorScheme.text.primaryColor,
                letterSpacing = 0.2.sp
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = if (isError) {
                        ColorScheme.colorScheme.alertAlphaColor
                    } else ColorScheme.colorScheme.textField.background,
                    shape = RoundedCornerShape(16.dp)
                )
                .border(
                    width = 1.dp,
                    color = if (isError) {
                        ColorScheme.colorScheme.alertColor
                    } else {
                        Color.Transparent
                    },
                    shape = RoundedCornerShape(16.dp)
                )
                .height(56.dp)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (value.isEmpty()) {
                Text(
                    text = label,
                    style = TextStyle(
                        lineHeight = 19.6.sp,
                        fontFamily = UrbanistFamily,
                        color = ColorScheme.colorScheme.textField.placeholder,
                        letterSpacing = 0.2.sp
                    )
                )
            } else {
                Text(
                    text = value,
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 22.4.sp,
                        fontFamily = UrbanistFamily,
                        fontWeight = FontWeight(600),
                        color = ColorScheme.colorScheme.textField.text,
                        letterSpacing = 0.2.sp
                    )
                )
            }

            DefaultIcon(
                type = illustrationType,
                tint = if (value.isNotEmpty()) {
                    ColorScheme.colorScheme.icon.color
                } else ColorScheme.colorScheme.icon.default
            )
        }

        if (isError) {
            Text(
                text = error,
                modifier = Modifier
                    .padding(start = 16.dp, top = 4.dp),
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

@PreviewLightDark
@Composable
private fun TextFieldClickUIPreview() {
    HelloTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(ColorScheme.colorScheme.screen.backgroundPrimary)
        ) {
            TextFieldClickUI(
                modifier = Modifier
                    .padding(32.dp),
                value = "Masculino",
                label = "Gênero",
                illustrationType = IC_RIGHT,
                error = "Gênero inválido",
                isError = true,
                onClick = {}
            )
        }
    }
}