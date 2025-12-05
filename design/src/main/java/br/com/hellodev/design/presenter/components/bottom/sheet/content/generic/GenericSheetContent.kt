package br.com.hellodev.design.presenter.components.bottom.sheet.content.generic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.com.hellodev.core.enums.sheet.SheetOrientationType
import br.com.hellodev.design.presenter.components.bottom.sheet.body.BodyBottomSheet
import br.com.hellodev.design.presenter.components.bottom.sheet.header.HeaderBottomSheet
import br.com.hellodev.design.presenter.components.button.PrimaryButton
import br.com.hellodev.design.presenter.components.button.SecondaryButton
import br.com.hellodev.design.presenter.components.spacer.HorizontalSpacer
import br.com.hellodev.design.presenter.components.spacer.VerticalSpacer
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.ShapeBottomSheet
import br.com.hellodev.domain.model.sheet.DefaultSheetModel

@Composable
fun GenericSheetContent(
    modifier: Modifier = Modifier,
    sheet: DefaultSheetModel,
    orientation: SheetOrientationType = SheetOrientationType.VERTICAL,
    onFirstClick: () -> Unit,
    onSecondClick: (() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 32.dp
            )
            .background(ColorScheme.colorScheme.screen.backgroundSecondary),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderBottomSheet(title = sheet.title)

        BodyBottomSheet(message = sheet.message.orEmpty())

        when (orientation) {
            SheetOrientationType.VERTICAL -> {
                onSecondClick?.let {
                    SecondaryButton(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = sheet.secondButtonText.orEmpty(),
                        onClick = it
                    )

                    VerticalSpacer(size = 12)
                }

                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = sheet.firstButtonText.orEmpty(),
                    onClick = onFirstClick
                )
            }

            SheetOrientationType.HORIZONTAL -> {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    onSecondClick?.let {
                        SecondaryButton(
                            modifier = Modifier
                                .weight(1f),
                            text = sheet.secondButtonText.orEmpty(),
                            onClick = it
                        )

                        HorizontalSpacer(size = 12)
                    }

                    PrimaryButton(
                        modifier = Modifier
                            .weight(1f),
                        text = sheet.firstButtonText.orEmpty(),
                        onClick = onFirstClick
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun GenericSheetPreview() {
    HelloTheme{
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
                GenericSheetContent(
                    sheet = DefaultSheetModel(
                        title = "Não foi possível efetuar o login",
                        message = "Por favor, tente novamente em alguns instantes.",
                        firstButtonText = "Tentar novamente",
                        secondButtonText = "Ok, entendi"
                    ),
                    onFirstClick = {},
                    onSecondClick = {}
                )
            }
        }
    }
}