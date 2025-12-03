package br.com.hellodev.onboarding.presenter.features.onboarding.screen

import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.hellodev.design.model.slider.SliderItem
import br.com.hellodev.design.presenter.components.button.PrimaryButton
import br.com.hellodev.design.presenter.components.loading.CircularLoadingScreen
import br.com.hellodev.design.presenter.components.slide.WelcomeSlideUI
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.onboarding.R
import br.com.hellodev.onboarding.presenter.features.onboarding.action.WelcomeAction
import br.com.hellodev.onboarding.presenter.features.onboarding.state.WelcomeUiState
import br.com.hellodev.onboarding.presenter.features.onboarding.viewmodel.WelcomeViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun WelcomeScreen(
    navigateToAuthentication: () -> Unit
) {
    val viewModel = koinViewModel<WelcomeViewModel>()
    val uiState by viewModel.state.collectAsState()
    val activity = LocalActivity.current

    LaunchedEffect(uiState.viewed) {
        if (uiState.viewed) {
            navigateToAuthentication()
        }
    }

    WelcomeContent(
        uiState = uiState,
        action = viewModel::dispatchAction,
        onBackPressed = {
            activity?.finish()
        }
    )
}

@Composable
private fun WelcomeContent(
    uiState: WelcomeUiState,
    action: (WelcomeAction) -> Unit,
    onBackPressed: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState { uiState.slideItems.size }

    BackHandler {
        if (pagerState.currentPage > 0) {
            scope.launch {
                pagerState.animateScrollToPage(pagerState.currentPage - 1)
            }
        } else {
            onBackPressed()
        }
    }

    when {
        uiState.isLoading -> {
            CircularLoadingScreen()
        }

        else -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(ColorScheme.colorScheme.screen.backgroundPrimary),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WelcomeSlideUI(
                    modifier = Modifier
                        .weight(1f),
                    slideItems = uiState.slideItems,
                    pagerState = pagerState
                )

                PrimaryButton(
                    text = if (pagerState.currentPage != uiState.slideItems.size - 1) {
                        stringResource(R.string.label_skip_button_welcome_screen)
                    } else stringResource(R.string.label_next_button_welcome_screen),
                    modifier = Modifier
                        .padding(
                            bottom = 32.dp,
                            start = 24.dp,
                            end = 24.dp
                        )
                        .windowInsetsPadding(WindowInsets.navigationBars),
                    onClick = {
                        scope.launch {
                            val nextPage = pagerState.currentPage + 1
                            if (nextPage < uiState.slideItems.size) {
                                pagerState.animateScrollToPage(nextPage)
                            } else {
                                action(WelcomeAction.OnboardingViewed)
                            }
                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun WelcomePreview() {
    HelloTheme(isDarkTheme = false) {
        WelcomeContent(
            uiState = WelcomeUiState(
                isLoading = false,
                slideItems = SliderItem.getSlideItems
            ),
            action = {},
            onBackPressed = {}
        )
    }
}