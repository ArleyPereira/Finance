package br.com.hellodev.main.presenter.features.applications.status.screen

import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.hellodev.core.enums.tag.TagType
import br.com.hellodev.design.presenter.components.bar.top.TopAppBarUI
import br.com.hellodev.design.presenter.components.button.PrimaryButton
import br.com.hellodev.design.presenter.components.divider.HorizontalDividerUI
import br.com.hellodev.design.presenter.components.item.job.details.JobDetailsItemUI
import br.com.hellodev.design.presenter.components.loading.CircularLoadingScreen
import br.com.hellodev.design.presenter.components.tag.TagUI
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily
import br.com.hellodev.domain.model.job.item.JobItemDomain
import br.com.hellodev.main.presenter.features.applications.status.action.ApplicationStatusAction
import br.com.hellodev.main.presenter.features.applications.status.state.ApplicationStatusState
import br.com.hellodev.main.presenter.features.applications.status.viewmodel.ApplicationStatusViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ApplicationStatusScreen(
    onBackPressed: () -> Unit
) {
    val viewModel = koinViewModel<ApplicationStatusViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    ApplicationStatusContent(
        state = state,
        action = viewModel::dispatchAction,
        onBackPressed = onBackPressed
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationStatusContent(
    state: ApplicationStatusState,
    action: (ApplicationStatusAction) -> Unit,
    onBackPressed: () -> Unit
) {
    when {
        state.isLoading -> {
            CircularLoadingScreen()
        }

        else -> {
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
                            .background(ColorScheme.colorScheme.screen.backgroundPrimary)
                            .windowInsetsPadding(WindowInsets.navigationBars)
                    ) {
                        HorizontalDividerUI()

                        PrimaryButton(
                            text = "Aguardando...",
                            modifier = Modifier
                                .padding(
                                    start = 24.dp,
                                    end = 24.dp,
                                    top = 24.dp,
                                    bottom = 32.dp
                                ),
                            enabled = false,
                            onClick = {  }
                        )
                    }
                },
                containerColor = ColorScheme.colorScheme.screen.backgroundPrimary,
                content = { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(paddingValues),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        state.job?.let { JobDetailsItemUI(job = it) }

                        HorizontalDividerUI(
                            modifier = Modifier
                                .padding(horizontal = 24.dp)
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            text = "Status da sua inscrição",
                            style = TextStyle(
                                fontSize = 18.sp,
                                lineHeight = 25.2.sp,
                                fontFamily = UrbanistFamily,
                                fontWeight = FontWeight(600),
                                color = ColorScheme.colorScheme.text.primaryColor,
                                textAlign = TextAlign.Center,
                                letterSpacing = 0.2.sp
                            )
                        )

                        Text(
                            text = "Inscrição: 25/12/2025",
                            style = TextStyle(
                                fontSize = 12.sp,
                                lineHeight = 19.6.sp,
                                fontFamily = UrbanistFamily,
                                fontWeight = FontWeight(500),
                                color = ColorScheme.colorScheme.text.primaryColor,
                                textAlign = TextAlign.Center,
                                letterSpacing = 0.2.sp
                            )
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        TagUI(
                            text = "Inscrição enviada",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp),
                            shape = RoundedCornerShape(16.dp),
                            type = TagType.entries.random(),
                            isAlpha = true,
                            paddingValues = PaddingValues(
                                vertical = 16.dp
                            ),
                            textStyle = TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 21.6.sp,
                                fontWeight = FontWeight(700)
                            )
                        )

                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun ApplicationStatusPreview() {
    HelloTheme {
        ApplicationStatusContent(
            state = ApplicationStatusState(
                isLoading = false,
                job = JobItemDomain.items.random()
            ),
            action = {},
            onBackPressed = {}
        )
    }
}