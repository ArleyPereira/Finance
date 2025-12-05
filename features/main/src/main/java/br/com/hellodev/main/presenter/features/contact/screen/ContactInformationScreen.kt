package br.com.hellodev.main.presenter.features.contact.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.hellodev.core.enums.input.InputType.EMAIL
import br.com.hellodev.core.enums.input.InputType.PHONE
import br.com.hellodev.core.functions.inputErrorMessage
import br.com.hellodev.design.presenter.components.bar.top.TopAppBarUI
import br.com.hellodev.design.presenter.components.button.PrimaryButton
import br.com.hellodev.design.presenter.components.divider.HorizontalDividerUI
import br.com.hellodev.design.presenter.components.textfield.default.TextFieldUI
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.util.transformation.MaskVisualTransformation
import br.com.hellodev.design.presenter.util.transformation.MaskVisualTransformation.Companion.PHONE_MASK
import br.com.hellodev.main.presenter.features.contact.action.ContactInformationAction
import br.com.hellodev.main.presenter.features.contact.state.ContactInformationState
import br.com.hellodev.main.presenter.features.contact.viewmodel.ContactInformationViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ContactInformationScreen(
    onBackPressed: () -> Unit
) {
    val viewModel = koinViewModel<ContactInformationViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    ContactInformationContent(
        state = state,
        action = viewModel::dispatchAction,
        onBackPressed = onBackPressed
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactInformationContent(
    state: ContactInformationState,
    action: (ContactInformationAction) -> Unit,
    onBackPressed: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarUI(
                title = "Informações de contato",
                onBackPressed = onBackPressed
            )
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(ColorScheme.colorScheme.screen.backgroundPrimary)
                    .windowInsetsPadding(WindowInsets.navigationBars)
                    //.imePadding()
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
                    onClick = { action(ContactInformationAction.Update) }
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
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                TextFieldUI(
                    value = state.phone,
                    label = "Telefone",
                    isError = state.inputError == PHONE,
                    error = stringResource(inputErrorMessage(PHONE)),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Next
                    ),
                    maxLength = MaskVisualTransformation.PHONE_MASK_SIZE,
                    visualTransformation = MaskVisualTransformation(PHONE_MASK),
                    onValueChange = {
                        action(ContactInformationAction.OnTextFieldChanged(it, PHONE))
                    }
                )

                TextFieldUI(
                    value = state.email,
                    label = "E-mail",
                    isError = state.inputError == EMAIL,
                    error = stringResource(inputErrorMessage(EMAIL)),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Done
                    ),
                    onValueChange = {
                        action(ContactInformationAction.OnTextFieldChanged(it, EMAIL))
                    }
                )
            }
        }
    )
}

@PreviewLightDark
@Composable
private fun ContactInformationPreview() {
    HelloTheme {
        ContactInformationContent(
            state = ContactInformationState(),
            action = {},
            onBackPressed = {}
        )
    }
}