package br.com.hellodev.design.presenter.components.bottom.sheet.content.selector_day

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.com.hellodev.design.presenter.components.bottom.sheet.body.BodyBottomSheet
import br.com.hellodev.design.presenter.components.bottom.sheet.header.HeaderBottomSheet
import br.com.hellodev.design.presenter.components.button.PrimaryButton
import br.com.hellodev.design.presenter.components.button.SecondaryButton
import br.com.hellodev.design.presenter.components.selector.DaySelector
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.domain.model.sheet.DefaultSheetModel

@Composable
fun SelectorDayBottomSheet(
    modifier: Modifier = Modifier,
    sheet: DefaultSheetModel,
    selectedDay: Int?,
    onDaySelected: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(ColorScheme.colorScheme.screen.backgroundPrimary),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderBottomSheet(title = sheet.title)

        BodyBottomSheet(message = sheet.message.orEmpty())

        DaySelector(
            selectedDay = selectedDay,
            onDaySelected = onDaySelected
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 24.dp
                ),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SecondaryButton(
                text = sheet.secondButtonText.orEmpty(),
                modifier = Modifier
                    .weight(1f),
                onClick = {}
            )

            PrimaryButton(
                text = sheet.firstButtonText.orEmpty(),
                modifier = Modifier
                    .weight(1f),
                onClick = {}
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun CouponBottomSheetPreview() {
    var selectedDay by remember { mutableIntStateOf(7) }

    HelloTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(ColorScheme.colorScheme.screen.backgroundPrimary),
            verticalArrangement = Arrangement.Bottom
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(ColorScheme.colorScheme.screen.backgroundSecondary)
            ) {
                SelectorDayBottomSheet(
                    sheet = DefaultSheetModel(
                        title = "Fechamento",
                        message = "Selecione o dia de fechamento da fatura do seu cartão",
                        firstButtonText = "Selecionar",
                        secondButtonText = "Fechar"
                    ),
                    selectedDay = selectedDay,
                    onDaySelected = { selectedDay = it }
                )
            }
        }
    }
}