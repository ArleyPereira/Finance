package br.com.hellodev.profile.presenter.features.profile.screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.hellodev.core.enums.illustration.IllustrationType
import br.com.hellodev.core.enums.input.InputType.EMAIL
import br.com.hellodev.core.enums.input.InputType.FIRST_NAME
import br.com.hellodev.core.enums.input.InputType.LAST_NAME
import br.com.hellodev.core.enums.input.InputType.PHONE
import br.com.hellodev.core.functions.inputErrorMessage
import br.com.hellodev.design.presenter.components.bar.top.TopAppBarUI
import br.com.hellodev.design.presenter.components.bottom.screen.BottomScreenUI
import br.com.hellodev.design.presenter.components.button.PrimaryButton
import br.com.hellodev.design.presenter.components.icon.default.DefaultIcon
import br.com.hellodev.design.presenter.components.image.ImageUI
import br.com.hellodev.design.presenter.components.textfield.default.TextFieldUI
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.util.transformation.MaskVisualTransformation
import br.com.hellodev.design.presenter.util.transformation.MaskVisualTransformation.Companion.PHONE_MASK
import br.com.hellodev.design.presenter.util.transformation.MaskVisualTransformation.Companion.PHONE_MASK_SIZE
import br.com.hellodev.profile.R
import br.com.hellodev.profile.presenter.features.profile.action.ProfileAction
import br.com.hellodev.profile.presenter.features.profile.state.ProfileState
import br.com.hellodev.profile.presenter.features.profile.viewmodel.ProfileViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    navigateToCountryScreen: () -> Unit,
    onBackPressed: () -> Unit
) {
    val viewModel = koinViewModel<ProfileViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    ProfileContent(
        state = state,
        action = viewModel::dispatchAction,
        navigateToCountryScreen = navigateToCountryScreen,
        onBackPressed = onBackPressed
    )
}

@Composable
private fun ProfileContent(
    state: ProfileState,
    action: (ProfileAction) -> Unit,
    navigateToCountryScreen: () -> Unit,
    onBackPressed: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            action(ProfileAction.OnChangeImage(uri ?: Uri.EMPTY))
        }
    )

    Scaffold(
        topBar = {
            TopAppBarUI(
                title = stringResource(R.string.label_title_edit_profile_screen),
                onClick = onBackPressed
            )
        },
        bottomBar = {
            BottomScreenUI(
                content = {
                    PrimaryButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                vertical = 24.dp,
                                horizontal = 16.dp
                            ),
                        text = stringResource(R.string.label_button_update_edit_profile_screen),
                        onClick = {
                            focusManager.clearFocus()
                            action(ProfileAction.Update)
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
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(160.dp)
                ) {
                    ImageUI(
                        modifier = Modifier
                            .size(160.dp),
                        imageModel = if (state.selectedImageUri != Uri.EMPTY) {
                            state.selectedImageUri
                        } else state.photo.ifEmpty {
                            R.drawable.placeholder_photo_profile
                        },
                        contentScale = ContentScale.Crop,
                        shape = CircleShape,
                        isLoading = state.isLoadingImage,
                        previewPlaceholder = painterResource(R.drawable.placeholder_photo_profile),
                        onClick = {
                            imagePickerLauncher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        }
                    )

                    DefaultIcon(
                        type = IllustrationType.IC_EDIT_FILL,
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.BottomEnd)
                            .offset(x = (-4).dp, y = (-4).dp),
                        tint = ColorScheme.colorScheme.defaultColor
                    )
                }

                TextFieldUI(
                    value = state.firstName,
                    label = stringResource(R.string.label_input_first_name_edit_profile_screen),
                    isError = state.inputError == FIRST_NAME,
                    error = stringResource(inputErrorMessage(FIRST_NAME)),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = {
                        action(ProfileAction.OnTextFieldChanged(it, FIRST_NAME))
                    }
                )

                TextFieldUI(
                    value = state.lastName,
                    label = stringResource(R.string.label_input_last_name_edit_profile_screen),
                    isError = state.inputError == LAST_NAME,
                    error = stringResource(inputErrorMessage(LAST_NAME)),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = {
                        action(ProfileAction.OnTextFieldChanged(it, LAST_NAME))
                    }
                )

                TextFieldUI(
                    value = state.phone,
                    label = stringResource(R.string.label_input_phone_edit_profile_screen),
                    error = stringResource(inputErrorMessage(PHONE)),
                    isError = state.inputError == PHONE,
                    maxLength = PHONE_MASK_SIZE,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    visualTransformation = MaskVisualTransformation(PHONE_MASK),
                    onValueChange = {
                        action(ProfileAction.OnTextFieldChanged(it, PHONE))
                    }
                )

                TextFieldUI(
                    value = state.email,
                    enabled = false,
                    label = stringResource(R.string.label_input_email_edit_profile_screen),
                    error = stringResource(inputErrorMessage(EMAIL)),
                    isError = state.inputError == EMAIL,
                    onValueChange = {}
                )
            }
        }
    )
}

@PreviewLightDark
@Composable
private fun ProfilePreview() {
    HelloTheme {
        ProfileContent(
            state = ProfileState(
                firstName = "Arley",
                lastName = "Santana",
                email = "arley@gmail.com",
                phone = ""
            ),
            action = {},
            navigateToCountryScreen = {},
            onBackPressed = {}
        )
    }
}