package br.com.hellodev.authentication.presenter.features.signup.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.hellodev.authentication.R
import br.com.hellodev.authentication.presenter.features.signup.action.SignupAction
import br.com.hellodev.authentication.presenter.features.signup.event.SignupEvent
import br.com.hellodev.authentication.presenter.features.signup.state.SignupState
import br.com.hellodev.authentication.presenter.features.signup.viewmodel.SignupViewModel
import br.com.hellodev.core.enums.illustration.IllustrationType
import br.com.hellodev.core.enums.input.signup.SignupInputType
import br.com.hellodev.design.presenter.components.bar.top.TopAppBarUI
import br.com.hellodev.design.presenter.components.bottom.screen.BottomScreenUI
import br.com.hellodev.design.presenter.components.button.PrimaryButton
import br.com.hellodev.design.presenter.components.icon.default.getDrawableIllustration
import br.com.hellodev.design.presenter.components.snackbar.FeedbackUI
import br.com.hellodev.design.presenter.components.textfield.default.TextFieldUI
import br.com.hellodev.design.presenter.components.textfield.password.TextFieldPasswordUI
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.HelloTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignupScreen(
    navigateToMainScreen: () -> Unit,
    onBackPressed: () -> Unit,
) {
    val viewModel = koinViewModel<SignupViewModel>()
    val state by viewModel.state.collectAsState()
    val event by viewModel.event.collectAsStateWithLifecycle(initialValue = SignupEvent.Idle)

    when (event) {
        is SignupEvent.Navigation.Main -> {
            navigateToMainScreen()
        }

        else -> {}
    }

    SignupContent(
        state = state,
        action = viewModel::submitAction,
        onBackPressed = onBackPressed
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SignupContent(
    state: SignupState,
    action: (SignupAction) -> Unit,
    onBackPressed: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarUI(
                title = stringResource(R.string.label_title_signup_screen),
                onBackPressed = onBackPressed
            )
        },
        bottomBar = {
            BottomScreenUI(
                feedback = {
                    state.feedback?.let { feedback ->
                        if (feedback.show) {
                            FeedbackUI(
                                feedback = feedback,
                                onDismiss = { action(SignupAction.DismissFeedback) }
                            )
                        }
                    }
                },
                content = {
                    PrimaryButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 16.dp,
                                vertical = 24.dp
                            ),
                        text = stringResource(id = R.string.label_button_signup_screen),
                        isLoading = state.isLoading,
                        onClick = { action(SignupAction.OnSignup) }
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
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TextFieldUI(
                    modifier = Modifier,
                    value = state.firstName,
                    label = stringResource(R.string.label_input_first_name_signup_screen),
                    isError = state.inputError == SignupInputType.FIRST_NAME,
                    error = stringResource(R.string.message_first_name_invalid_format_signup_screen),
                    leadingIcon = {
                        Icon(
                            painter = getDrawableIllustration(IllustrationType.IC_PERSON_FILL),
                            contentDescription = null,
                            tint = ColorScheme.colorScheme.icon.default
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = {
                        action(
                            SignupAction.OnValueChange(
                                value = it,
                                type = SignupInputType.FIRST_NAME
                            )
                        )
                    }
                )

                TextFieldUI(
                    modifier = Modifier,
                    value = state.lastName,
                    label = stringResource(R.string.label_input_last_name_signup_screen),
                    isError = state.inputError == SignupInputType.LAST_NAME,
                    error = stringResource(R.string.message_last_name_invalid_format_signup_screen),
                    leadingIcon = {
                        Icon(
                            painter = getDrawableIllustration(IllustrationType.IC_PERSON_FILL),
                            contentDescription = null,
                            tint = ColorScheme.colorScheme.icon.default
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = {
                        action(
                            SignupAction.OnValueChange(
                                value = it,
                                type = SignupInputType.LAST_NAME
                            )
                        )
                    }
                )

                TextFieldUI(
                    modifier = Modifier,
                    value = state.email,
                    label = stringResource(R.string.label_input_email_signup_screen),
                    isError = state.inputError == SignupInputType.EMAIL,
                    error = stringResource(R.string.message_email_invalid_format_signup_screen),
                    leadingIcon = {
                        Icon(
                            painter = getDrawableIllustration(IllustrationType.IC_EMAIL_FILL),
                            contentDescription = null,
                            tint = ColorScheme.colorScheme.icon.default
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = {
                        action(
                            SignupAction.OnValueChange(
                                value = it,
                                type = SignupInputType.EMAIL
                            )
                        )
                    }
                )

                TextFieldPasswordUI(
                    modifier = Modifier,
                    value = state.password,
                    label = stringResource(id = R.string.label_input_password_signup_screen),
                    isError = state.inputError == SignupInputType.PASSWORD,
                    error = stringResource(R.string.message_password_invalid_format_signup_screen),
                    onValueChange = {
                        action(
                            SignupAction.OnValueChange(
                                value = it,
                                type = SignupInputType.PASSWORD
                            )
                        )
                    }
                )
            }
        }
    )
}

@PreviewLightDark
@Composable
private fun SignupPreview() {
    HelloTheme {
        SignupContent(
            state = SignupState(
                email = "arley.santana@hellodev.com.br",
                password = "123456"
            ),
            action = {},
            onBackPressed = {}
        )
    }
}