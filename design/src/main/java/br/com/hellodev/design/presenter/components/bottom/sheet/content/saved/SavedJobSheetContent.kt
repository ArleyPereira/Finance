package br.com.hellodev.design.presenter.components.bottom.sheet.content.saved

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.domain.model.job.item.JobItemDomain
import br.com.hellodev.design.presenter.components.bottom.sheet.drag.DragBottomSheet
import br.com.hellodev.design.presenter.components.button.PrimaryButton
import br.com.hellodev.design.presenter.components.button.SecondaryButton
import br.com.hellodev.design.presenter.components.divider.HorizontalDividerUI
import br.com.hellodev.design.presenter.components.item.job.item.JobItemUI
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.ShapeBottomSheet
import br.com.hellodev.design.presenter.theme.UrbanistFamily

@Composable
fun SavedJobSheetContent(
    modifier: Modifier = Modifier,
    job: JobItemDomain,
    onJobClick: (Int) -> Unit,
    onCancelClick: () -> Unit,
    onConfirmClick: () -> Unit
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
            text = "Remover de Salvo?",
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

        JobItemUI(
            modifier = Modifier
                .fillMaxWidth(),
            job = job,
            isSaved = true,
            colors = CardDefaults.cardColors(
                containerColor = ColorScheme.colorScheme.screen.backgroundPrimary
            ),
            onSaveClick = {},
            onClick = onJobClick
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            SecondaryButton(
                modifier = Modifier
                    .weight(1f),
                text = "Cancelar",
                onClick = onCancelClick
            )

            Spacer(modifier = Modifier.width(12.dp))

            PrimaryButton(
                modifier = Modifier
                    .weight(1f),
                text = "Sim, remover",
                onClick = onConfirmClick
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun SavedJobSheetContentPreview() {
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
                SavedJobSheetContent(
                    job = JobItemDomain.items.first(),
                    onJobClick = {},
                    onCancelClick = {},
                    onConfirmClick = {}
                )
            }
        }
    }
}