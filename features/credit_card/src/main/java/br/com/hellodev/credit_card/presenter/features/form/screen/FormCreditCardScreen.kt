package br.com.hellodev.credit_card.presenter.features.form.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.hellodev.core.enums.input.credit_card.CreditCardInputType.CLOSING_DAY
import br.com.hellodev.core.enums.input.credit_card.CreditCardInputType.DUE_DAY
import br.com.hellodev.core.enums.input.credit_card.CreditCardInputType.LIMIT
import br.com.hellodev.core.enums.input.credit_card.CreditCardInputType.NAME
import br.com.hellodev.credit_card.R
import br.com.hellodev.credit_card.presenter.features.form.action.FormCreditCardAction
import br.com.hellodev.credit_card.presenter.features.form.state.FormCreditCardState
import br.com.hellodev.credit_card.presenter.features.form.viewmodel.FormCreditCardViewModel
import br.com.hellodev.design.presenter.components.bar.top.TopAppBarUI
import br.com.hellodev.design.presenter.components.bottom.screen.BottomScreenUI
import br.com.hellodev.design.presenter.components.button.PrimaryButton
import br.com.hellodev.design.presenter.components.header.HeaderScreen
import br.com.hellodev.design.presenter.components.textfield.decimal.CurrencyTextFieldUI
import br.com.hellodev.design.presenter.components.textfield.default.TextFieldUI
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.HelloTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FormCreditCardScreen(
    onBackPressed: () -> Unit
) {
    val viewModel = koinViewModel<FormCreditCardViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    FormCreditCardContent(
        state = state,
        action = viewModel::dispatchAction,
        onBackPressed = onBackPressed
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormCreditCardContent(
    state: FormCreditCardState,
    action: (FormCreditCardAction) -> Unit,
    onBackPressed: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            TopAppBarUI(
                title = "Novo cartão",
                onBackPressed = onBackPressed
            )
        },
        bottomBar = {
            BottomScreenUI(
                isVisible = state.isLoading,
                content = {
                    PrimaryButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                vertical = 24.dp,
                                horizontal = 16.dp
                            ),
                        text = "Salvar",
                        onClick = {
                            focusManager.clearFocus()
                            action(FormCreditCardAction.SaveCreditCard)
                        }
                    )
                }
            )
        },
        containerColor = ColorScheme.colorScheme.screen.backgroundPrimary,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                HeaderScreen(title = "Informe os dados do cartão que deseja cadastrar.")

                TextFieldUI(
                    value = state.name,
                    label = "Nome do cartão",
                    error = stringResource(R.string.message_name_invalid_form_credit_card_screen),
                    isError = state.inputError == NAME,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = {
                        action(FormCreditCardAction.OnValueChange(it, NAME))
                    }
                )

                CurrencyTextFieldUI(
                    value = state.limit,
                    label = "Limite do cartão",
                    isError = state.inputError == LIMIT,
                    error = stringResource(R.string.message_limit_invalid_form_credit_card_screen),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = { limit ->
                        action(FormCreditCardAction.OnLimitChange(limit))
                    }
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    TextFieldUI(
                        modifier = Modifier
                            .weight(1f),
                        value = state.closingDay,
                        label = "Fechamento",
                        error = stringResource(R.string.message_closing_day_invalid_format_signup_screen),
                        isError = state.inputError == CLOSING_DAY,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        onValueChange = {
                            action(FormCreditCardAction.OnValueChange(it, CLOSING_DAY))
                        }
                    )

                    TextFieldUI(
                        modifier = Modifier
                            .weight(1f),
                        value = state.dueDay,
                        label = "Vencimento",
                        error = stringResource(R.string.message_due_day_invalid_format_signup_screen),
                        isError = state.inputError == DUE_DAY,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        onValueChange = {
                            action(FormCreditCardAction.OnValueChange(it, DUE_DAY))
                        }
                    )
                }
            }
        }
    )
}

@PreviewLightDark
@Composable
private fun FormCreditCardPreview() {
    HelloTheme {
        FormCreditCardContent(
            state = FormCreditCardState(),
            action = {},
            onBackPressed = {}
        )
    }
}