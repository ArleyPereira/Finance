package br.com.hellodev.main.presenter.features.main.account.screen

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.hellodev.core.enums.account.AccountCardActionType.ADD
import br.com.hellodev.core.enums.account.AccountCardActionType.EDIT
import br.com.hellodev.core.enums.account.AccountCardType
import br.com.hellodev.core.enums.account.AccountCardType.CONTACT_INFORMATION
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_EDIT_LINE
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_EMAIL_LINE
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_LOCATION_LINE
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_PHONE_LINE
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_SETTINGS
import br.com.hellodev.design.R
import br.com.hellodev.design.presenter.components.bar.top.TopAppBarUI
import br.com.hellodev.design.presenter.components.card.account.AccountCard
import br.com.hellodev.design.presenter.components.divider.HorizontalDividerUI
import br.com.hellodev.design.presenter.components.icon.default.DefaultIcon
import br.com.hellodev.design.presenter.components.icon.default.getDrawableIllustration
import br.com.hellodev.design.presenter.components.image.ImageUI
import br.com.hellodev.design.presenter.components.upload.UploadUI
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily
import br.com.hellodev.design.presenter.util.transformation.MaskVisualTransformation
import br.com.hellodev.design.presenter.util.transformation.MaskVisualTransformation.Companion.PHONE_MASK
import br.com.hellodev.main.presenter.features.account.viewmodel.AccountViewModel
import br.com.hellodev.main.presenter.features.main.account.action.AccountAction
import br.com.hellodev.main.presenter.features.main.account.state.AccountState
import org.koin.androidx.compose.koinViewModel

@Composable
fun AccountScreen(
    navigateToProfileScreen: () -> Unit,
    navigateToContactInformationScreen: () -> Unit,
    navigateToSummaryScreen: () -> Unit,
    navigateToSalaryExpectationScreen: () -> Unit
) {
    val viewModel = koinViewModel<AccountViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    val pickPdfLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        uri?.let {
            viewModel.dispatchAction(AccountAction.OnSelectedCurriculum(it))
        }
    }

    AccountContent(
        state = state,
        action = viewModel::dispatchAction,
        onUploadClick = {
            pickPdfLauncher.launch(arrayOf("application/pdf"))
        },
        navigateToProfileScreen = navigateToProfileScreen,
        navigateToContactInformationScreen = navigateToContactInformationScreen,
        navigateToSummaryScreen = navigateToSummaryScreen,
        navigateToSalaryExpectationScreen = navigateToSalaryExpectationScreen,
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AccountContent(
    state: AccountState,
    action: (AccountAction) -> Unit,
    onUploadClick: () -> Unit,
    navigateToProfileScreen: () -> Unit,
    navigateToContactInformationScreen: () -> Unit,
    navigateToSummaryScreen: () -> Unit,
    navigateToSalaryExpectationScreen: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBarUI(
                modifier = Modifier,
                title = "Perfil",
                showNavigationIcon = false,
                actions = {
                    Row(
                        modifier = Modifier
                            .padding(end = 8.dp)
                    ) {
                        IconButton(
                            onClick = { },
                            content = {
                                DefaultIcon(
                                    type = IC_SETTINGS,
                                    tint = ColorScheme.colorScheme.icon.color
                                )
                            }
                        )
                    }
                },
                onClick = {}
            )
        },
        containerColor = ColorScheme.colorScheme.screen.backgroundPrimary,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
                    .padding(
                        start = 24.dp,
                        end = 24.dp,
                        bottom = WindowInsets.navigationBars.asPaddingValues()
                            .calculateBottomPadding() * 3
                    ),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navigateToProfileScreen() },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ImageUI(
                        modifier = Modifier
                            .size(60.dp),
                        imageModel = R.drawable.person,
                        contentScale = ContentScale.Crop,
                        previewPlaceholder = painterResource(id = R.drawable.person),
                        shape = CircleShape,
                        onClick = {}
                    )

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 16.dp)
                    ) {
                        Text(
                            text = "Andrew Ainsley",
                            style = TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 28.8.sp,
                                fontFamily = UrbanistFamily,
                                fontWeight = FontWeight(700),
                                color = ColorScheme.colorScheme.text.primaryColor
                            )
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "UI/UX Designer at Paypal Inc.",
                            style = TextStyle(
                                fontSize = 12.sp,
                                lineHeight = 22.4.sp,
                                fontFamily = UrbanistFamily,
                                fontWeight = FontWeight(500),
                                color = ColorScheme.colorScheme.text.primaryColor,
                                letterSpacing = 0.2.sp
                            )
                        )
                    }

                    DefaultIcon(
                        type = IC_EDIT_LINE,
                        onClick = {}
                    )
                }

                HorizontalDividerUI(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                )

                AccountCard(
                    title = "Informações de contato",
                    cardType = CONTACT_INFORMATION,
                    actionType = EDIT,
                    content = {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Icon(
                                    painter = getDrawableIllustration(type = IC_LOCATION_LINE),
                                    contentDescription = null,
                                    tint = ColorScheme.colorScheme.icon.color
                                )

                                Text(
                                    text = "",
                                    style = TextStyle(
                                        lineHeight = 22.4.sp,
                                        fontFamily = UrbanistFamily,
                                        fontWeight = FontWeight(500),
                                        color = ColorScheme.colorScheme.text.primaryColor,
                                        letterSpacing = 0.2.sp
                                    )
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 12.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Icon(
                                    painter = getDrawableIllustration(type = IC_PHONE_LINE),
                                    contentDescription = null,
                                    tint = ColorScheme.colorScheme.icon.color
                                )

                                Text(
                                    text = MaskVisualTransformation.maskText(
                                        value = state.user?.phone ?: "",
                                        mask = PHONE_MASK
                                    ),
                                    style = TextStyle(
                                        lineHeight = 22.4.sp,
                                        fontFamily = UrbanistFamily,
                                        fontWeight = FontWeight(500),
                                        color = ColorScheme.colorScheme.text.primaryColor,
                                        letterSpacing = 0.2.sp
                                    )
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Icon(
                                    painter = getDrawableIllustration(type = IC_EMAIL_LINE),
                                    contentDescription = null,
                                    tint = ColorScheme.colorScheme.icon.color
                                )

                                Text(
                                    text = state.user?.email ?: "",
                                    style = TextStyle(
                                        lineHeight = 22.4.sp,
                                        fontFamily = UrbanistFamily,
                                        fontWeight = FontWeight(500),
                                        color = ColorScheme.colorScheme.text.primaryColor,
                                        letterSpacing = 0.2.sp
                                    )
                                )
                            }
                        }
                    },
                    onActionClick = navigateToContactInformationScreen
                )

                AccountCard(
                    title = "Resumo",
                    cardType = AccountCardType.SUMMARY,
                    actionType = EDIT,
                    content = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = LoremIpsum(25).values.first(),
                                style = TextStyle(
                                    lineHeight = 22.4.sp,
                                    fontFamily = UrbanistFamily,
                                    fontWeight = FontWeight(500),
                                    color = ColorScheme.colorScheme.text.primaryColor,
                                    letterSpacing = 0.2.sp
                                )
                            )
                        }
                    },
                    onActionClick = navigateToSummaryScreen
                )

                AccountCard(
                    title = "Expectativa salarial",
                    cardType = AccountCardType.EXPECTED_SALARY,
                    actionType = EDIT,
                    content = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "R$ 10,000 - R$ 25,000 /mês",
                                style = TextStyle(
                                    lineHeight = 22.4.sp,
                                    fontFamily = UrbanistFamily,
                                    fontWeight = FontWeight(500),
                                    color = ColorScheme.colorScheme.text.primaryColor,
                                    letterSpacing = 0.2.sp
                                )
                            )
                        }
                    },
                    onActionClick = navigateToSalaryExpectationScreen
                )

                AccountCard(
                    title = "Experiência profissional",
                    cardType = AccountCardType.WORK_EXPERIENCE,
                    actionType = ADD,
                    content = {},
                    onActionClick = {}
                )

                AccountCard(
                    title = "Educação",
                    cardType = AccountCardType.EDUCATION,
                    actionType = ADD,
                    content = {},
                    onActionClick = {}
                )

                AccountCard(
                    title = "Projetos",
                    cardType = AccountCardType.PROJECTS,
                    actionType = ADD,
                    content = {},
                    onActionClick = {}
                )

                AccountCard(
                    title = "Idiomas",
                    cardType = AccountCardType.LANGUAGES,
                    actionType = ADD,
                    content = {},
                    onActionClick = {}
                )

                AccountCard(
                    title = "Tecnologias",
                    cardType = AccountCardType.SKILLS,
                    actionType = EDIT,
                    content = {},
                    onActionClick = {}
                )

                AccountCard(
                    title = "Curriculo",
                    cardType = AccountCardType.CV_RESUME,
                    actionType = null,
                    content = {
                        UploadUI(
                            isLoading = state.isUploading,
                            uri = state.uriPdf,
                            onLoadClick = onUploadClick,
                            onDeleteClick = {
                                action(AccountAction.OnDeleteCurriculum)
                            }
                        )
                    },
                    onActionClick = {}
                )
            }
        }
    )
}

@PreviewLightDark
@Composable
private fun AccountPreview() {
    HelloTheme {
        AccountContent(
            state = AccountState(),
            action = {},
            onUploadClick = {},
            navigateToProfileScreen = {},
            navigateToContactInformationScreen = {},
            navigateToSummaryScreen = {},
            navigateToSalaryExpectationScreen = {}
        )
    }
}