package br.com.hellodev.design.presenter.components.bottom.sheet.drag

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.ColorScheme

@Composable
fun DragBottomSheet(
    modifier: Modifier = Modifier
) {
    Spacer(
        modifier = modifier
            .padding(
                top = 8.dp,
                bottom = 24.dp
            )
            .height(3.dp)
            .width(38.dp)
            .clip(CircleShape)
            .background(ColorScheme.colorScheme.divider.color)
    )
}

@PreviewLightDark
@Composable
private fun DragBottomSheetPreview() {
    HelloTheme {
        DragBottomSheet()
    }
}