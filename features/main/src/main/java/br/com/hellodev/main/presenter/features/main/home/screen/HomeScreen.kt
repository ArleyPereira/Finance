package br.com.hellodev.main.presenter.features.main.home.screen

import android.annotation.SuppressLint
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.hellodev.design.presenter.components.icon.notification.NotificationIcon
import br.com.hellodev.design.presenter.components.image.ImageUI
import br.com.hellodev.design.presenter.components.item.job.section.HorizontalJobSectionUI
import br.com.hellodev.design.presenter.components.item.job.section.verticalJobSectionUI
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily
import br.com.hellodev.domain.model.banner.BannerDomain
import br.com.hellodev.domain.model.job.section.JobSectionDomain
import br.com.hellodev.domain.model.user.User
import br.com.hellodev.main.R
import br.com.hellodev.main.presenter.features.home.action.HomeAction
import br.com.hellodev.main.presenter.features.main.home.state.HomeState
import br.com.hellodev.main.presenter.features.main.home.viewmodel.HomeViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    paddingValues: PaddingValues = PaddingValues(),
    navigateToDetails: (Int) -> Unit,
    navigateToSearchScreen: (String) -> Unit
) {
    val viewModel = koinViewModel<HomeViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    HomeContent(
        paddingValues = paddingValues,
        state = state,
        action = viewModel::dispatchAction,
        navigateToDetails = navigateToDetails,
        navigateToSearchScreen = navigateToSearchScreen
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun HomeContent(
    paddingValues: PaddingValues = PaddingValues(),
    state: HomeState,
    action: (HomeAction) -> Unit,
    navigateToDetails: (Int) -> Unit,
    navigateToSearchScreen: (String) -> Unit
) {
    val pagerState = rememberPagerState { BannerDomain.items.size }

    LaunchedEffect(pagerState) {
        while (true) {
            delay(4000)
            var newPosition = pagerState.currentPage + 1
            if (newPosition > BannerDomain.items.size - 1) {
                newPosition = 0
            }
            pagerState.animateScrollToPage(
                page = newPosition,
                animationSpec = tween(1000)
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
            .background(ColorScheme.colorScheme.screen.backgroundPrimary)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 16.dp,
                    start = 24.dp,
                    end = 24.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ImageUI(
                modifier = Modifier
                    .size(48.dp),
                imageModel = state.profile?.avatar,
                contentScale = ContentScale.Crop,
                shape = CircleShape,
                previewPlaceholder = painterResource(id = R.drawable.ic_person),
                onClick = {}
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Olá,",
                    style = TextStyle(
                        lineHeight = 22.4.sp,
                        fontFamily = UrbanistFamily,
                        color = ColorScheme.colorScheme.text.primaryColor,
                        letterSpacing = 0.2.sp
                    )
                )

                Text(
                    text = state.profile?.firstName ?: "",
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 24.sp,
                        fontFamily = UrbanistFamily,
                        fontWeight = FontWeight(700),
                        color = ColorScheme.colorScheme.text.primaryColor
                    )
                )
            }

            NotificationIcon(
                viewed = false,
                onClick = {}
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(
                bottom = WindowInsets.navigationBars.asPaddingValues()
                    .calculateBottomPadding() * 3
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                state.recommendation?.let {
                    HorizontalJobSectionUI(
                        section = it,
                        onJobClick = navigateToDetails,
                        onSaveClick = {},
                        onAllClick = {}
                    )
                }

            }

            state.recent?.let {
                verticalJobSectionUI(
                    section = it,
                    categorySelected = state.categorySelected,
                    search = state.query,
                    onCategorySelected = { category ->
                        action(HomeAction.OnCategorySelected(category))
                    },
                    onJobClick = navigateToDetails,
                    onSaveClick = {},
                    onAllClick = {}
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun HomePreview() {
    HelloTheme {
        HomeContent(
            state = HomeState(
                isLoading = false,
                query = "",
                profile = User.userDefault,
                banners = BannerDomain.items,
                recommendation = JobSectionDomain.items[0],
                recent = JobSectionDomain.items[1]
            ),
            action = {},
            navigateToDetails = {},
            navigateToSearchScreen = {}
        )
    }
}