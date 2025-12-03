package br.com.hellodev.design.presenter.components.icon.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.com.hellodev.design.R
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.borderDefault

@Composable
fun NotificationIcon(
    modifier: Modifier = Modifier,
    viewed: Boolean = false,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .borderDefault()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClick
            )
            .padding(12.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_notification),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp),
            tint = ColorScheme.colorScheme.icon.color
        )

        if (!viewed) {
            Box(
                modifier = Modifier
                    .size(6.dp)
                    .background(
                        color = ColorScheme.colorScheme.alertColor,
                        shape = CircleShape
                    )
                    .align(Alignment.TopEnd)
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun NotificationIconPreview() {
    HelloTheme {
        Column(
            modifier = Modifier
                .background(ColorScheme.colorScheme.screen.backgroundPrimary)
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NotificationIcon(
                viewed = false,
                onClick = {}
            )
        }
    }
}