package br.com.hellodev.design.presenter.components.bar.bottom

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import br.com.hellodev.design.R
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily

@Composable
fun RowScope.BottomBarItemUI(
    modifier: Modifier = Modifier,
    selectedIcon: Painter,
    unselectedIcon: Painter,
    label: String? = null,
    isSelect: Boolean = false,
    onClick: () -> Unit
) {
    NavigationBarItem(
        selected = isSelect,
        onClick = onClick,
        icon = {
            if (isSelect) {
                Icon(
                    painter = selectedIcon,
                    contentDescription = label,
                    tint = ColorScheme.colorScheme.defaultColor
                )
            } else {
                Icon(
                    painter = unselectedIcon,
                    contentDescription = label,
                    tint = ColorScheme.colorScheme.greyscale500Color
                )
            }
        },
        modifier = modifier,
        label = {
            label?.let {
                Text(
                    text = it,
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontFamily = UrbanistFamily,
                        fontWeight = FontWeight.Bold,
                        color = if (isSelect) {
                            ColorScheme.colorScheme.defaultColor
                        } else {
                            ColorScheme.colorScheme.greyscale500Color
                        },
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.2.sp
                    )
                )
            }
        },
        colors = NavigationBarItemDefaults.colors(
            indicatorColor = Color.Transparent
        ),
        interactionSource = NoRippleInteractionSource()
    )
}

@Preview
@Composable
private fun BottomBarItemUIPreview() {
    HelloTheme {
        BottomAppBar(
            actions = {
                BottomBarItemUI(
                    selectedIcon = painterResource(R.drawable.ic_home_fill),
                    unselectedIcon = painterResource(R.drawable.ic_home_line),
                    label = "Início",
                    isSelect = true,
                    onClick = {}
                )

                BottomBarItemUI(
                    selectedIcon = painterResource(R.drawable.ic_saved_fill),
                    unselectedIcon = painterResource(R.drawable.ic_saved_line),
                    label = "Salvos",
                    isSelect = false,
                    onClick = {}
                )

                BottomBarItemUI(
                    selectedIcon = painterResource(R.drawable.ic_application_fill),
                    unselectedIcon = painterResource(R.drawable.ic_application_line),
                    label = "Salvos",
                    isSelect = false,
                    onClick = {}
                )
            },
            containerColor = ColorScheme.colorScheme.screen.backgroundPrimary
        )
    }
}