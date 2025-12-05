package br.com.hellodev.design.presenter.components.bottom.sheet.header

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.design.R
import br.com.hellodev.design.presenter.components.bottom.sheet.drag.DragBottomSheet
import br.com.hellodev.design.presenter.components.divider.HorizontalDividerUI
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily

@Composable
fun HeaderBottomSheet(
    modifier: Modifier = Modifier,
    title: String?
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DragBottomSheet()

        Text(
            text = title ?: stringResource(R.string.text_tile_generic_sheet_content),
            style = TextStyle(
                fontSize = 24.sp,
                lineHeight = 28.8.sp,
                fontFamily = UrbanistFamily,
                fontWeight = FontWeight(700),
                color = ColorScheme.colorScheme.text.primaryColor,
                textAlign = TextAlign.Center
            )
        )

        HorizontalDividerUI(
            modifier = Modifier
                .padding(vertical = 24.dp)
        )
    }
}

@PreviewLightDark
@Composable
private fun HeaderBottomSheetPreview() {
    HelloTheme {
        HeaderBottomSheet(
            title = "Remover dos favoritos?"
        )
    }
}