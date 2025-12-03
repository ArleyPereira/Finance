package br.com.hellodev.authentication.presenter.features.login.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.hellodev.authentication.R
import br.com.hellodev.authentication.presenter.features.login.action.LoginAction
import br.com.hellodev.authentication.presenter.features.login.event.LoginEvent
import br.com.hellodev.authentication.presenter.features.login.state.LoginState
import br.com.hellodev.authentication.presenter.features.login.viewmodel.LoginViewModel
import br.com.hellodev.core.enums.illustration.IllustrationType
import br.com.hellodev.core.enums.input.login.LoginInputType
import br.com.hellodev.design.presenter.components.bottom.screen.BottomScreenUI
import br.com.hellodev.design.presenter.components.button.PrimaryButton
import br.com.hellodev.design.presenter.components.button.TextButtonUI
import br.com.hellodev.design.presenter.components.icon.default.DefaultIcon
import br.com.hellodev.design.presenter.components.snackbar.FeedbackUI
import br.com.hellodev.design.presenter.components.textfield.default.TextFieldUI
import br.com.hellodev.design.presenter.components.textfield.password.TextFieldPasswordUI
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.HelloTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    navigateToMainScreen: () -> Unit,
    navigateToSignupScreen: () -> Unit
) {
    val viewModel = koinViewModel<LoginViewModel>()
    val state by viewModel.state.collectAsState()
    val event by viewModel.event.collectAsStateWithLifecycle(initialValue = LoginEvent.Idle)

    when (event) {
        is LoginEvent.Navigation.Main -> {
            navigateToMainScreen()
        }

        else -> {}
    }

    LoginContent(
        state = state,
        action = viewModel::submitAction,
        navigateToSignupScreen = navigateToSignupScreen
    )
}

@Composable
private fun LoginContent(
    state: LoginState,
    action: (LoginAction) -> Unit,
    navigateToSignupScreen: () -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomScreenUI(
                feedback = {
                    state.feedback?.let { feedback ->
                        if (feedback.show) {
                            FeedbackUI(
                                feedback = feedback,
                                onDismiss = { action(LoginAction.DismissFeedback) }
                            )
                        }
                    }
                },
                content = {}
            )
        },
        containerColor = ColorScheme.colorScheme.screen.backgroundPrimary,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                TextFieldUI(
                    modifier = Modifier,
                    value = state.email,
                    label = stringResource(id = R.string.label_input_email_login_screen),
                    isError = state.inputError == LoginInputType.EMAIL,
                    error = stringResource(R.string.message_email_invalid_format_login_screen),
                    leadingIcon = {
                        DefaultIcon(type = IllustrationType.IC_EMAIL_FILL)
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = {
                        action(
                            LoginAction.OnValueChange(
                                value = it,
                                type = LoginInputType.EMAIL
                            )
                        )
                    }
                )

                TextFieldPasswordUI(
                    modifier = Modifier
                        .padding(vertical = 16.dp),
                    value = state.password,
                    label = stringResource(id = R.string.label_input_password_login_screen),
                    isError = state.inputError == LoginInputType.PASSWORD,
                    error = stringResource(R.string.message_password_invalid_format_login_screen),
                    onValueChange = {
                        action(
                            LoginAction.OnValueChange(
                                value = it,
                                type = LoginInputType.PASSWORD
                            )
                        )
                    }
                )

                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = stringResource(id = R.string.label_button_login_screen),
                    isLoading = state.isLoading,
                    onClick = { action(LoginAction.OnSignIn) }
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextButtonUI(
                        text = stringResource(R.string.label_forgot_password_login_screen),
                        textColor = ColorScheme.colorScheme.defaultColor,
                        onClick = {}
                    )

                    TextButtonUI(
                        text = stringResource(R.string.label_sign_up_login_screen),
                        textColor = ColorScheme.colorScheme.defaultColor,
                        onClick = navigateToSignupScreen
                    )
                }
            }
        }
    )
}

@PreviewLightDark
@Composable
private fun LoginPreview() {
    HelloTheme {
        LoginContent(
            state = LoginState(),
            action = {},
            navigateToSignupScreen = {}
        )
    }
}