package br.com.hellodev.main.presenter.features.summary.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.hellodev.design.presenter.components.bar.top.TopAppBarUI
import br.com.hellodev.design.presenter.components.button.PrimaryButton
import br.com.hellodev.design.presenter.components.divider.HorizontalDividerUI
import br.com.hellodev.design.presenter.components.textfield.default.TextFieldUI
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.main.presenter.features.summary.action.SummaryAction
import br.com.hellodev.main.presenter.features.summary.state.SummaryState
import br.com.hellodev.main.presenter.features.summary.viewmodel.SummaryViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SummaryScreen(
    onBackPressed: () -> Unit
) {
    val viewModel = koinViewModel<SummaryViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    SummaryContent(
        state = state,
        action = viewModel::dispatchAction,
        onBackPressed = onBackPressed
    )
}

@Composable
fun SummaryContent(
    state: SummaryState,
    action: (SummaryAction) -> Unit,
    onBackPressed: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarUI(
                title = "Informações de contato",
                onClick = onBackPressed
            )
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .background(ColorScheme.colorScheme.screen.backgroundPrimary)
                    .windowInsetsPadding(WindowInsets.navigationBars)
            ) {
                HorizontalDividerUI()

                PrimaryButton(
                    modifier = Modifier
                        .padding(
                            start = 24.dp,
                            end = 24.dp,
                            top = 24.dp,
                            bottom = 32.dp
                        ),
                    text = "Salvar",
                    onClick = {  }
                )
            }
        },
        containerColor = ColorScheme.colorScheme.screen.backgroundPrimary,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(paddingValues)
                    .padding(24.dp)
            ) {
                TextFieldUI(
                    value = LoremIpsum(50).values.first(),
                    label = "Resume",
                    isError = false,
                    singleLine = false,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    onValueChange = {

                    }
                )
            }
        }
    )
}

@PreviewLightDark
@Composable
private fun SummaryPreview() {
    HelloTheme {
        SummaryContent(
            state = SummaryState(),
            action = {},
            onBackPressed = {}
        )
    }
}