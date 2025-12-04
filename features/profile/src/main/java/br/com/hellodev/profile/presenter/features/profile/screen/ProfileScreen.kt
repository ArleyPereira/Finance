package br.com.hellodev.profile.presenter.features.profile.screen

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.hellodev.core.enums.menu.MenuType
import br.com.hellodev.core.enums.menu.MenuType.CREDIT_CARDS
import br.com.hellodev.core.enums.menu.MenuType.DARK_MODE
import br.com.hellodev.core.enums.menu.MenuType.EDIT_PROFILE
import br.com.hellodev.core.enums.menu.MenuType.LANGUAGE
import br.com.hellodev.core.enums.menu.MenuType.LOGOUT
import br.com.hellodev.core.functions.string.getFullName
import br.com.hellodev.design.extensions.modifier.shimmerPlaceholder
import br.com.hellodev.design.model.menu.MenuProfileItems
import br.com.hellodev.design.presenter.components.menu.MenuItemUI
import br.com.hellodev.design.presenter.components.spacer.VerticalSpacer
import br.com.hellodev.design.presenter.components.user.UserAvatar
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily
import br.com.hellodev.domain.model.user.User
import br.com.hellodev.profile.presenter.features.profile.action.ProfileAction
import br.com.hellodev.profile.presenter.features.profile.state.ProfileState
import br.com.hellodev.profile.presenter.features.profile.viewmodel.ProfileViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    paddingValues: PaddingValues = PaddingValues(),
    navigateToEditProfileScreen: () -> Unit
) {
    val viewModel = koinViewModel<ProfileViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    val pickPdfLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        uri?.let {
            viewModel.dispatchAction(ProfileAction.OnSelectedCurriculum(it))
        }
    }

    AccountContent(
        paddingValues = paddingValues,
        state = state,
        action = viewModel::dispatchAction,
        onUploadClick = {
            pickPdfLauncher.launch(arrayOf("application/pdf"))
        },
        onItemClick = {
            when (it) {
                EDIT_PROFILE -> {
                    navigateToEditProfileScreen()
                }

                CREDIT_CARDS -> {

                }

                DARK_MODE -> {

                }

                LOGOUT -> {

                }

                else -> {}
            }
        }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AccountContent(
    paddingValues: PaddingValues = PaddingValues(),
    state: ProfileState,
    action: (ProfileAction) -> Unit,
    onUploadClick: () -> Unit,
    onItemClick: (MenuType) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .padding(
                start = paddingValues.calculateStartPadding(LayoutDirection.Ltr),
                end = paddingValues.calculateEndPadding(LayoutDirection.Ltr)
            ),
        containerColor = ColorScheme.colorScheme.screen.backgroundPrimary,
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                contentPadding = PaddingValues(
                    top = paddingValues.calculateTopPadding() + 16.dp,
                    bottom = paddingValues.calculateBottomPadding()
                ),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        UserAvatar(
                            modifier = Modifier
                                .size(80.dp),
                            avatar = state.user?.avatar,
                            firstName = state.user?.firstName,
                            lastName = state.user?.lastName,
                            isLoading = state.isLoading
                        )

                        VerticalSpacer(size = 4)

                        Text(
                            modifier = Modifier
                                .shimmerPlaceholder(visible = state.isLoading),
                            text = getFullName(
                                firstName = state.user?.firstName,
                                lastName = state.user?.lastName
                            ),
                            style = TextStyle(
                                fontSize = 20.sp,
                                lineHeight = 24.sp,
                                fontFamily = UrbanistFamily,
                                fontWeight = FontWeight.Bold,
                                color = ColorScheme.colorScheme.text.primaryColor,
                                textAlign = TextAlign.Center
                            )
                        )

                        Text(
                            modifier = Modifier
                                .shimmerPlaceholder(visible = state.isLoading),
                            text = state.user?.email.orEmpty(),
                            style = TextStyle(
                                lineHeight = 19.6.sp,
                                fontFamily = UrbanistFamily,
                                fontWeight = FontWeight.Medium,
                                color = ColorScheme.colorScheme.text.primaryColor,
                                textAlign = TextAlign.Center,
                                letterSpacing = 0.2.sp
                            )
                        )
                    }
                }

                items(MenuProfileItems.items()) { item ->
                    when (item.type) {
                        LANGUAGE -> {

                        }

                        else -> {
                            MenuItemUI(
                                illustration = item.illustration,
                                label = stringResource(item.label),
                                description = stringResource(item.description),
                                onClick = {
                                    when (item.type) {
                                        LOGOUT -> {

                                        }

                                        DARK_MODE -> {

                                        }

                                        else -> {
                                            onItemClick(item.type)
                                        }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    )
}

@PreviewLightDark
@Composable
private fun AccountPreview() {
    HelloTheme {
        AccountContent(
            state = ProfileState(
                isLoading = false,
                user = User.userDefault
            ),
            action = {},
            onUploadClick = {},
            onItemClick = {}
        )
    }
}