package br.com.hellodev.design.presenter.components.bottom.sheet.content.apply

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.design.presenter.components.bottom.sheet.drag.DragBottomSheet
import br.com.hellodev.design.presenter.components.button.PrimaryButton
import br.com.hellodev.design.presenter.components.button.SecondaryButton
import br.com.hellodev.design.presenter.components.divider.HorizontalDividerUI
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.ShapeBottomSheet
import br.com.hellodev.design.presenter.theme.UrbanistFamily

@Composable
fun ApplyJobSheetContent(
    modifier: Modifier = Modifier,
    onCurriculumClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = 24.dp,
                end = 24.dp,
                bottom = 32.dp
            )
            .background(ColorScheme.colorScheme.screen.backgroundSecondary),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DragBottomSheet()

        Text(
            text = "Apply Job",
            style = TextStyle(
                fontSize = 24.sp,
                lineHeight = 28.8.sp,
                fontFamily = UrbanistFamily,
                fontWeight = FontWeight(700),
                color = ColorScheme.colorScheme.text.primaryColor
            )
        )

        HorizontalDividerUI(
            modifier = Modifier
                .padding(vertical = 24.dp)
        )

        SecondaryButton(
            modifier = Modifier,
            text = "Aplicar com curriculo",
            onClick = onCurriculumClick
        )

        Spacer(modifier = Modifier.height(12.dp))

        PrimaryButton(
            modifier = Modifier,
            text = "Aplicar com perfil",
            onClick = onProfileClick
        )
    }
}

@PreviewLightDark
@Composable
private fun ApplyJobSheetPreview() {
    HelloTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorScheme.colorScheme.screen.backgroundPrimary),
            verticalArrangement = Arrangement.Bottom
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = ColorScheme.colorScheme.screen.backgroundSecondary,
                        shape = ShapeBottomSheet
                    )
            ) {
                ApplyJobSheetContent(
                    onCurriculumClick = {},
                    onProfileClick = {}
                )
            }
        }
    }
}