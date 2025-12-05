package br.com.hellodev.job_details.presenter.features.applying.screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import br.com.hellodev.core.enums.dialog.applying.ApplyingDialogType.APPLYING_ERROR
import br.com.hellodev.core.enums.dialog.applying.ApplyingDialogType.APPLYING_SUCCESS
import br.com.hellodev.core.enums.illustration.IllustrationType
import br.com.hellodev.core.enums.input.InputType.EMAIL
import br.com.hellodev.core.enums.input.InputType.FULL_NAME
import br.com.hellodev.core.enums.input.InputType.UPLOAD
import br.com.hellodev.core.functions.inputErrorMessage
import br.com.hellodev.design.presenter.components.bar.top.TopAppBarUI
import br.com.hellodev.design.presenter.components.button.PrimaryButton
import br.com.hellodev.design.presenter.components.dialog.applying.ApplyingDialog
import br.com.hellodev.design.presenter.components.divider.HorizontalDividerUI
import br.com.hellodev.design.presenter.components.icon.default.DefaultIcon
import br.com.hellodev.design.presenter.components.textfield.default.TextFieldUI
import br.com.hellodev.design.presenter.components.upload.UploadUI
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.job_details.R
import br.com.hellodev.job_details.presenter.features.applying.action.ApplyingAction
import br.com.hellodev.job_details.presenter.features.applying.state.ApplyingState
import br.com.hellodev.job_details.presenter.features.applying.viewmodel.ApplyingViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ApplyingScreen(
    onBackPressed: () -> Unit
) {
    val viewModel = koinViewModel<ApplyingViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    val pickPdfLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        uri?.let {
            viewModel.dispatchAction(ApplyingAction.OnSelectedCurriculum(it))
        }
    }

    ApplyingContent(
        state = state,
        action = viewModel::dispatchAction,
        onUploadClick = {
            pickPdfLauncher.launch(arrayOf("application/pdf"))
        },
        onBackPressed = onBackPressed
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplyingContent(
    state: ApplyingState,
    action: (ApplyingAction) -> Unit,
    onUploadClick: () -> Unit,
    onBackPressed: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarUI(
                title = stringResource(R.string.label_title_applying_screen),
                onBackPressed = onBackPressed
            )
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(ColorScheme.colorScheme.screen.backgroundPrimary.copy(alpha = 0.7f))
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
                    text = stringResource(R.string.label_send_applying_screen),
                    isLoading = state.isLoading,
                    onClick = { action(ApplyingAction.Send) }
                )
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .background(ColorScheme.colorScheme.screen.backgroundPrimary)
                    .padding(24.dp)
                    .padding(paddingValues)
                    .imePadding(),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                TextFieldUI(
                    modifier = Modifier,
                    value = state.fullName,
                    isError = state.inputError == FULL_NAME,
                    error = stringResource(inputErrorMessage(FULL_NAME)),
                    label = stringResource(R.string.label_full_name_applying_screen),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = {
                        action(ApplyingAction.OnTextFieldChanged(it, FULL_NAME))
                    }
                )

                TextFieldUI(
                    modifier = Modifier,
                    value = state.email,
                    isError = state.inputError == EMAIL,
                    error = stringResource(inputErrorMessage(EMAIL)),
                    label = stringResource(R.string.label_email_applying_screen),
                    trailingIcon = {
                        DefaultIcon(type = IllustrationType.IC_EMAIL_FILL)
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
                    onValueChange = {
                        action(ApplyingAction.OnTextFieldChanged(it, EMAIL))
                    }
                )

                UploadUI(
                    label = stringResource(R.string.label_upload_curriculum_applying_screen),
                    isLoading = state.isUploading,
                    uri = state.uriPdf,
                    isError = state.inputError == UPLOAD,
                    error = "Selecione o seu curriculo",
                    onLoadClick = onUploadClick,
                    onDeleteClick = {
                        action(ApplyingAction.OnDeleteCurriculum)
                    }
                )

                TextFieldUI(
                    modifier = Modifier,
                    value = state.coverLetter,
                    label = stringResource(R.string.label_cover_letter_applying_screen),
                    singleLine = false,
                    onValueChange = {
                        action(ApplyingAction.OnTextFieldChanged(it, null))
                    }
                )
            }

            state.dialogDS?.let {
                ApplyingDialog(
                    dialog = it,
                    onClick = {
                        when (state.dialogDS.type) {
                            APPLYING_ERROR -> {
                                action(ApplyingAction.Send)
                            }

                            APPLYING_SUCCESS -> {
                                // navegar para a tela de inscrições
                            }
                        }
                    },
                    onDismissRequest = {
                        action(ApplyingAction.ClearDialog)
                    }
                )
            }
        }
    )
}

@PreviewLightDark
@Composable
private fun ApplyingPreview() {
    HelloTheme {
        ApplyingContent(
            state = ApplyingState(
                //inputError = UPLOAD,
                //uriPdf = Uri.parse("")
            ),
            action = {},
            onUploadClick = {},
            onBackPressed = {}
        )
    }
}