package br.com.hellodev.design.presenter.components.section.header

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.design.extensions.modifier.shimmerPlaceholder
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily

@Composable
fun HeaderMoreSectionUI(
    modifier: Modifier = Modifier,
    rightTitle: String = "",
    leftTitle: String,
    isLoadingEffect: Boolean = false,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = leftTitle,
            modifier = Modifier
                .shimmerPlaceholder(visible = isLoadingEffect),
            style = TextStyle(
                lineHeight = 17.6.sp,
                fontFamily = UrbanistFamily,
                fontWeight = FontWeight(700),
                color = ColorScheme.colorScheme.text.primaryColor
            )
        )

        if (rightTitle.isNotEmpty()) {
            Text(
                text = rightTitle,
                modifier = Modifier
                    .shimmerPlaceholder(visible = isLoadingEffect)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        enabled = !isLoadingEffect,
                        onClick = onClick
                    ),
                style = TextStyle(
                    lineHeight = 22.4.sp,
                    fontFamily = UrbanistFamily,
                    fontWeight = FontWeight(700),
                    color = ColorScheme.colorScheme.defaultColor,
                    letterSpacing = 0.2.sp
                )
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun HeaderHorizontalCourseSectionUIPreview() {
    HelloTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(ColorScheme.colorScheme.screen.backgroundPrimary),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderMoreSectionUI(
                modifier = Modifier
                    .padding(16.dp),
                leftTitle = "Cursos recomendados",
                rightTitle = "Ver mais",
                onClick = {}
            )
        }
    }
}