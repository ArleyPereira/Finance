package br.com.hellodev.design.presenter.components.bottom.sheet.content.selector_day

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import br.com.hellodev.design.presenter.components.bottom.sheet.body.BodyBottomSheet
import br.com.hellodev.design.presenter.components.bottom.sheet.header.HeaderBottomSheet
import br.com.hellodev.design.presenter.components.selector.DaySelector
import br.com.hellodev.design.presenter.components.spacer.VerticalSpacer
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.HelloTheme

@Composable
fun SelectorDayBottomSheet(
    modifier: Modifier = Modifier,
    title: String,
    message: String,
    selectedDay: Int?,
    onDaySelected: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(ColorScheme.colorScheme.screen.backgroundPrimary),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderBottomSheet(title = title)

        BodyBottomSheet(message = message)

        DaySelector(
            selectedDay = selectedDay,
            onDaySelected = onDaySelected
        )

        VerticalSpacer(size = 24)
    }
}

@PreviewLightDark
@Composable
private fun SelectorDayBottomSheetPreview() {
    var selectedDay by remember { mutableIntStateOf(7) }

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
                    .background(ColorScheme.colorScheme.screen.backgroundSecondary)
            ) {
                SelectorDayBottomSheet(
                    title = "Fechamento",
                    message = "Selecione o dia de fechamento da fatura do seu cartão",
                    selectedDay = selectedDay,
                    onDaySelected = { selectedDay = it }
                )
            }
        }
    }
}