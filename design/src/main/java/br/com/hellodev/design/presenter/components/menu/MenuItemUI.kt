package br.com.hellodev.design.presenter.components.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.core.enums.illustration.IllustrationType
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_ARROW_RIGHT
import br.com.hellodev.design.presenter.components.icon.default.getDrawableIllustration
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily
import br.com.hellodev.design.presenter.theme.borderStrokeDefault

@Composable
fun MenuItemUI(
    modifier: Modifier = Modifier,
    illustration: IllustrationType,
    label: String,
    description: String? = null,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = ColorScheme.colorScheme.menuItem.background
        ),
        border = borderStrokeDefault(),
        content = {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = getDrawableIllustration(type = illustration),
                    contentDescription = null,
                    modifier = Modifier
                        .height(22.dp),
                    tint = ColorScheme.colorScheme.icon.color
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(end = 32.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = label,
                        style = TextStyle(
                            lineHeight = 25.2.sp,
                            fontFamily = UrbanistFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = ColorScheme.colorScheme.text.primaryColor,
                            letterSpacing = 0.2.sp
                        )
                    )

                    description?.let {
                        Text(
                            text = it,
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = UrbanistFamily,
                                color = ColorScheme.colorScheme.text.secondaryColor,
                                letterSpacing = 0.2.sp
                            ),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                Icon(
                    painter = getDrawableIllustration(type = IC_ARROW_RIGHT),
                    contentDescription = null,
                    tint = ColorScheme.colorScheme.icon.color
                )
            }
        }
    )
}

@PreviewLightDark
@Composable
private fun MenuItemUIPreview() {
    HelloTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(ColorScheme.colorScheme.screen.backgroundPrimary)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MenuItemUI(
                illustration = IllustrationType.IC_PERSON_LINE,
                label = "Editar perfil",
                description = "Edite as informações do seu perfil de teste de preview",
                onClick = {}
            )
        }
    }
}