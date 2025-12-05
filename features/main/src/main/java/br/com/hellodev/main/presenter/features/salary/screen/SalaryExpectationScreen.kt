package br.com.hellodev.main.presenter.features.salary.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.hellodev.core.enums.illustration.IllustrationType
import br.com.hellodev.core.enums.input.InputType.SALARY_MAXIMUM
import br.com.hellodev.core.enums.input.InputType.SALARY_MINIMUM
import br.com.hellodev.core.functions.inputErrorMessage
import br.com.hellodev.design.presenter.components.bar.top.TopAppBarUI
import br.com.hellodev.design.presenter.components.button.PrimaryButton
import br.com.hellodev.design.presenter.components.divider.HorizontalDividerUI
import br.com.hellodev.design.presenter.components.textfield.decimal.CurrencyTextFieldUI
import br.com.hellodev.design.presenter.components.textfield.dropdown.TextFieldDropdown
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.main.R
import br.com.hellodev.main.presenter.features.salary.action.SalaryExpectationAction
import br.com.hellodev.main.presenter.features.salary.state.SalaryExpectationState
import br.com.hellodev.main.presenter.features.salary.viewmodel.SalaryExpectationViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SalaryExpectationScreen(
    onBackPressed: () -> Unit
) {
    val viewModel = koinViewModel<SalaryExpectationViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    SalaryExpectationContent(
        state = state,
        action = viewModel::dispatchAction,
        onBackPressed = onBackPressed
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalaryExpectationContent(
    state: SalaryExpectationState,
    action: (SalaryExpectationAction) -> Unit,
    onBackPressed: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarUI(
                title = stringResource(R.string.label_title_top_app_bar_salary_expectation_screen),
                onBackPressed = onBackPressed
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
                    text = stringResource(R.string.label_save_button_salary_expectation_screen),
                    onClick = { action(SalaryExpectationAction.Save) }
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
                    .imePadding(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CurrencyTextFieldUI(
                    value = state.minimumSalary,
                    label = stringResource(R.string.label_minimum_salary_expectation_screen),
                    isError = state.inputError == SALARY_MINIMUM,
                    error = stringResource(inputErrorMessage(SALARY_MINIMUM)),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = {
                        //action(SalaryExpectationAction.OnTextFieldChanged(it, SALARY_MINIMUM))
                    }
                )

                CurrencyTextFieldUI(
                    value = state.maximumSalary,
                    label = stringResource(R.string.label_maximum_salary_expectation_screen),
                    isError = state.inputError == SALARY_MAXIMUM,
                    error = stringResource(inputErrorMessage(SALARY_MAXIMUM)),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = {
                        //action(SalaryExpectationAction.OnTextFieldChanged(it, SALARY_MAXIMUM))
                    }
                )

                TextFieldDropdown(
                    items = state.frequencies,
                    label = stringResource(R.string.label_frequency_salary_expectation_screen),
                    illustrationType = IllustrationType.IC_RIGHT,
                    onOptionSelected = {
                        //action(SalaryExpectationAction.OnTextFieldChanged(it, SALARY_FREQUENCY))
                    }
                )
            }
        }
    )
}

@PreviewLightDark
@Composable
private fun SalaryExpectationPreview() {
    HelloTheme {
        SalaryExpectationContent(
            state = SalaryExpectationState(),
            action = {},
            onBackPressed = {}
        )
    }
}