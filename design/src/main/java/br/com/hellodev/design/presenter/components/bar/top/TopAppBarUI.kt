package br.com.hellodev.design.presenter.components.bar.top

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_ARROW_LEFT
import br.com.hellodev.design.presenter.components.icon.default.DefaultIcon
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarUI(
    modifier: Modifier = Modifier,
    title: String = "",
    showNavigationIcon: Boolean = true,
    actions: @Composable (RowScope.() -> Unit) = {},
    onClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 28.8.sp,
                    fontFamily = UrbanistFamily,
                    fontWeight = FontWeight.Bold
                )
            )
        },
        modifier = modifier,
        actions = actions,
        navigationIcon = {
            if (showNavigationIcon) {
                IconButton(
                    onClick = onClick,
                    content = {
                        DefaultIcon(
                            type = IC_ARROW_LEFT,
                            tint = ColorScheme.colorScheme.icon.color,
                            onClick = onClick
                        )
                    }
                )
            } else {
                Spacer(modifier = Modifier.width(16.dp))
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = ColorScheme.colorScheme.topAppBar.background,
            navigationIconContentColor = ColorScheme.colorScheme.topAppBar.content,
            actionIconContentColor = ColorScheme.colorScheme.topAppBar.content,
            titleContentColor = ColorScheme.colorScheme.topAppBar.content
        )
    )
}

@PreviewLightDark
@Composable
private fun TopAppBarUIPreview() {
    HelloTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorScheme.colorScheme.screen.backgroundPrimary),
        ) {
            TopAppBarUI(
                title = "Fill Your Profile",
                showNavigationIcon = true,
                onClick = {}
            )
        }
    }
}