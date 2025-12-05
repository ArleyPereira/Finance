package br.com.hellodev.setup.presenter.features.expertise.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.hellodev.design.presenter.components.bar.top.TopAppBarUI
import br.com.hellodev.design.presenter.components.button.PrimaryButton
import br.com.hellodev.design.presenter.components.check.CheckBoxUi
import br.com.hellodev.design.presenter.components.divider.HorizontalDividerUI
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily
import br.com.hellodev.design.presenter.theme.borderStrokeDefault
import br.com.hellodev.domain.model.expertise.Expertise
import br.com.hellodev.setup.R
import br.com.hellodev.setup.presenter.features.expertise.action.ExpertiseAction
import br.com.hellodev.setup.presenter.features.expertise.state.ExpertiseState
import br.com.hellodev.setup.presenter.features.expertise.viewmodel.ExpertiseViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ExpertiseScreen(
    navigateToProfileScreen: () -> Unit,
    onBackPressed: () -> Unit
) {
    val viewModel = koinViewModel<ExpertiseViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    ExpertiseContent(
        state = state,
        action = viewModel::dispatchAction,
        navigateToProfileScreen = navigateToProfileScreen,
        onBackPressed = onBackPressed
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ExpertiseContent(
    state: ExpertiseState,
    action: (ExpertiseAction) -> Unit,
    navigateToProfileScreen: () -> Unit,
    onBackPressed: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarUI(
                onBackPressed = onBackPressed
            )
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
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
                    text = stringResource(R.string.text_button_next_expertise_screen),
                    enabled = state.selectedExpertises.isNotEmpty(),
                    onClick = navigateToProfileScreen
                )
            }
        },
        containerColor = ColorScheme.colorScheme.screen.backgroundPrimary,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(
                        horizontal = 24.dp
                    )
            ) {
                Text(
                    text = "Qual é a sua especialidade?",
                    style = TextStyle(
                        fontSize = 24.sp,
                        lineHeight = 38.4.sp,
                        fontFamily = UrbanistFamily,
                        fontWeight = FontWeight(700),
                        color = ColorScheme.colorScheme.text.primaryColor,
                        textAlign = TextAlign.Center
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Selecione sua área de especialização (até 5)",
                    style = TextStyle(
                        lineHeight = 25.2.sp,
                        fontFamily = UrbanistFamily,
                        color = ColorScheme.colorScheme.text.primaryColor,
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.2.sp
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                HorizontalDividerUI()

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(
                        vertical = 24.dp
                    )
                ) {
                    items(state.expertises) { expertise ->
                        val checked = state.selectedExpertises.contains(expertise)
                        val isSelectedMaxSize = state.selectedExpertises.size < 5

                        CheckBoxUi(
                            modifier = Modifier
                                .fillMaxWidth(),
                            checked = checked,
                            enabled = checked || isSelectedMaxSize,
                            text = expertise.name ?: "",
                            borderStroke = borderStrokeDefault(checked),
                            onClick = {
                                action(ExpertiseAction.OnSelect(expertise))
                            }
                        )
                    }
                }

            }
        }
    )
}

@PreviewLightDark
@Composable
private fun ExpertisePreview() {
    HelloTheme {
        ExpertiseContent(
            state = ExpertiseState(
                expertises = Expertise.items(),
                selectedExpertises = Expertise.items().take(5)
            ),
            action = {},
            navigateToProfileScreen = {},
            onBackPressed = {}
        )
    }
}