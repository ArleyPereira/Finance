package br.com.hellodev.design.presenter.components.bottom.sheet.body

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.sp
import br.com.hellodev.design.presenter.components.spacer.VerticalSpacer
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily

@Composable
fun BodyBottomSheet(
    modifier: Modifier = Modifier,
    message: String
) {
    Text(
        text = message,
        modifier = modifier,
        style = TextStyle(
            lineHeight = 22.4.sp,
            fontFamily = UrbanistFamily,
            color = ColorScheme.colorScheme.text.primaryColor,
            textAlign = TextAlign.Center,
            letterSpacing = 0.2.sp
        )
    )

    VerticalSpacer(size = 24)
}

@PreviewLightDark
@Composable
private fun BodyBottomSheetPreview() {
    HelloTheme {
        BodyBottomSheet(
            message = "A senha informada está incorreta"
        )
    }
}
