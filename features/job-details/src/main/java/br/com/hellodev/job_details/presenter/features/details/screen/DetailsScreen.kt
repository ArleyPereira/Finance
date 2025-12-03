package br.com.hellodev.job_details.presenter.features.details.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.hellodev.domain.model.job.item.JobItemDomain
import br.com.hellodev.core.enums.illustration.IllustrationType
import br.com.hellodev.design.presenter.components.bar.top.TopAppBarUI
import br.com.hellodev.design.presenter.components.bottom.sheet.content.apply.ApplyJobSheetContent
import br.com.hellodev.design.presenter.components.bottom.sheet.default.DefaultBottomSheet
import br.com.hellodev.design.presenter.components.button.PrimaryButton
import br.com.hellodev.design.presenter.components.divider.HorizontalDividerUI
import br.com.hellodev.design.presenter.components.icon.default.DefaultIcon
import br.com.hellodev.design.presenter.components.item.job.details.JobDetailsItemUI
import br.com.hellodev.design.presenter.components.loading.CircularLoadingScreen
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.iconTintColor
import br.com.hellodev.job_details.presenter.features.details.action.DetailsAction
import br.com.hellodev.job_details.presenter.features.details.state.DetailsState
import br.com.hellodev.job_details.presenter.features.details.viewmodel.DetailsViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreen(
    navigateToApplyingScreen: (Int) -> Unit,
    onBackPressed: () -> Unit
) {
    val viewModel = koinViewModel<DetailsViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    DetailsContent(
        state = state,
        action = viewModel::dispatchAction,
        navigateToApplyingScreen = navigateToApplyingScreen,
        onBackPressed = onBackPressed
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailsContent(
    state: DetailsState,
    action: (DetailsAction) -> Unit,
    navigateToApplyingScreen: (Int) -> Unit,
    onBackPressed: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    var showBottomSheet by remember { mutableStateOf(false) }
    var checked by remember { mutableStateOf(false) }

    when {
        state.isLoading -> {
            CircularLoadingScreen()
        }

        else -> {
            Scaffold(
                topBar = {
                    TopAppBarUI(
                        actions = {
                            Row(
                                modifier = Modifier
                                    .padding(end = 8.dp)
                            ) {
                                IconButton(
                                    onClick = { checked = !checked },
                                    content = {
                                        DefaultIcon(
                                            type = IllustrationType.IC_SEND,
                                            tint = ColorScheme.colorScheme.icon.color
                                        )
                                    }
                                )

                                IconButton(
                                    onClick = { checked = !checked },
                                    content = {
                                        DefaultIcon(
                                            type = if (checked) IllustrationType.IC_MARK_FILL else IllustrationType.IC_MARK_LINE,
                                            tint = iconTintColor(filled = checked)
                                        )
                                    }
                                )
                            }
                        },
                        onClick = onBackPressed
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
                            modifier = Modifier
                                .padding(
                                    start = 24.dp,
                                    end = 24.dp,
                                    top = 24.dp,
                                    bottom = 32.dp
                                ),
                            text = "Aplicar",
                            onClick = { showBottomSheet = true }
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
                    }

                    if (showBottomSheet) {
                        DefaultBottomSheet(
                            onDismissRequest = {
                                scope.launch { sheetState.hide() }.invokeOnCompletion {
                                    showBottomSheet = false
                                }
                            },
                            sheetState = sheetState,
                            content = {
                                ApplyJobSheetContent(
                                    onCurriculumClick = {
                                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                                            showBottomSheet = false
                                            navigateToApplyingScreen(state.job?.id ?: 0)
                                        }
                                    },
                                    onProfileClick = {
                                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                                            showBottomSheet = false
                                        }
                                    }
                                )
                            }
                        )
                    }
                }
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun DetailsPreview() {
    HelloTheme {
        DetailsContent(
            state = DetailsState(
                isLoading = false,
                job = JobItemDomain.items.random()
            ),
            action = {},
            navigateToApplyingScreen = {},
            onBackPressed = {}
        )
    }
}