package br.com.hellodev.design.presenter.components.bottom.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.com.hellodev.design.presenter.components.button.PrimaryButton
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.HelloTheme

@Composable
fun BottomScreenUI(
    modifier: Modifier = Modifier,
    isVisible: Boolean = false,
    feedback: @Composable (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)

    AnimatedVisibility(
        visible = !isVisible,
        enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
        exit = slideOutVertically(targetOffsetY = { it }) + fadeOut()
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.safeDrawing)
        ) {
            feedback?.invoke()

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape)
                    .background(
                        color = ColorScheme.colorScheme.screen.backgroundSecondary,
                        shape = shape
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    content()
                }
            )

            Spacer(
                modifier = Modifier
                    .windowInsetsPadding(WindowInsets.navigationBars)
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun BottomScreenUIPreview() {
    HelloTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorScheme.colorScheme.screen.backgroundPrimary),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BottomScreenUI(
                content = {
                    PrimaryButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 16.dp,
                                vertical = 24.dp
                            ),
                        text = "Continuar",
                        onClick = { }
                    )
                }
            )
        }
    }
}