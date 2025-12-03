package br.com.hellodev.design.presenter.components.dialog.applying

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import br.com.hellodev.core.enums.dialog.applying.ApplyingDialogType.APPLYING_ERROR
import br.com.hellodev.core.enums.dialog.applying.ApplyingDialogType.APPLYING_SUCCESS
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_ERROR
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_SUCCESS
import br.com.hellodev.design.model.dialog.applying.ApplyingDialogDS
import br.com.hellodev.design.presenter.components.button.PrimaryButton
import br.com.hellodev.design.presenter.components.button.SecondaryButton
import br.com.hellodev.design.presenter.components.icon.default.DefaultIcon
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily

@Composable
fun ApplyingDialog(
    modifier: Modifier = Modifier,
    dialog: ApplyingDialogDS,
    onClick: () -> Unit,
    onDismissRequest: () -> Unit
) {
    val icon = when (dialog.type) {
        APPLYING_ERROR -> IC_ERROR
        APPLYING_SUCCESS -> IC_SUCCESS
    }

    val titleColor = when (dialog.type) {
        APPLYING_ERROR -> ColorScheme.colorScheme.alertColor
        APPLYING_SUCCESS -> ColorScheme.colorScheme.defaultColor
    }

    Dialog(
        onDismissRequest = { onDismissRequest() },
        content = {
            Card(
                modifier = modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(48.dp),
                colors = CardDefaults.cardColors(
                    containerColor = ColorScheme.colorScheme.screen.backgroundSecondary
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    DefaultIcon(type = icon)

                    Spacer(modifier = Modifier.height(32.dp))

                    Text(
                        text = dialog.title,
                        style = TextStyle(
                            fontSize = 24.sp,
                            lineHeight = 28.8.sp,
                            fontFamily = UrbanistFamily,
                            fontWeight = FontWeight(700),
                            color = titleColor
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = dialog.description,
                        modifier = Modifier
                            .fillMaxWidth(),
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 22.4.sp,
                            fontFamily = UrbanistFamily,
                            color = ColorScheme.colorScheme.text.primaryColor,
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.2.sp
                        )
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    PrimaryButton(
                        text = dialog.firsButtonText,
                        onClick = onClick
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    SecondaryButton(
                        text = dialog.secondButtonText,
                        onClick = onDismissRequest
                    )
                }
            }
        }
    )
}

@PreviewLightDark
@Composable
private fun ApplyingDialogErrorPreview() {
    HelloTheme {
        ApplyingDialog(
            dialog = ApplyingDialogDS(
                title = "Ops, falhou!",
                description = "Verifique sua conexão com a Internet e tente novamente.",
                firsButtonText = "Tentar novamente",
                secondButtonText = "Cancelar",
                type = APPLYING_ERROR
            ),
            onClick = {},
            onDismissRequest = {}
        )
    }
}

@PreviewLightDark
@Composable
private fun ApplyingDialogSuccessPreview() {
    HelloTheme {
        ApplyingDialog(
            dialog = ApplyingDialogDS(
                title = "Parabéns!",
                description = "A sua candidatura foi submetida com sucesso. Você pode acompanhar o andamento da sua inscrição através do menu de aplicativos.",
                firsButtonText = "Minhas candidaturas",
                secondButtonText = "Cancelar",
                type = APPLYING_SUCCESS
            ),
            onClick = {},
            onDismissRequest = {}
        )
    }
}