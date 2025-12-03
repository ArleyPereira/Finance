package br.com.hellodev.design.presenter.components.slide

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.design.model.slider.SliderItem
import br.com.hellodev.design.presenter.components.indicator.SlideIndicator
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily

@Composable
fun WelcomeSlideUI(
    modifier: Modifier = Modifier,
    slideItems: List<SliderItem>,
    pagerState: PagerState
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ColorScheme.colorScheme.screen.backgroundPrimary),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HorizontalPager(
            state = pagerState,
            Modifier
                .fillMaxSize()
                .weight(1f),
            pageContent = {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Image(
                        painter = painterResource(slideItems[pagerState.currentPage].imageRes),
                        modifier = Modifier
                            .size(395.dp),
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = slideItems[pagerState.currentPage].title,
                        style = TextStyle(
                            fontSize = 32.sp,
                            lineHeight = 48.sp,
                            fontFamily = UrbanistFamily,
                            fontWeight = FontWeight.Bold,
                            color = ColorScheme.colorScheme.defaultColor,
                            textAlign = TextAlign.Center
                        )
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = slideItems[pagerState.currentPage].description,
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 25.2.sp,
                            fontFamily = UrbanistFamily,
                            fontWeight = FontWeight.Medium,
                            color = ColorScheme.colorScheme.text.primaryColor,
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.2.sp
                        )
                    )
                }
            }
        )

        SlideIndicator(
            modifier = Modifier
                .padding(32.dp),
            totalIndicators = slideItems.size,
            currentIndicator = pagerState.currentPage
        )
    }
}

@PreviewLightDark
@Composable
private fun WelcomeSlideUIPreview() {
    val slideItems = SliderItem.getSlideItems
    val pagerState = rememberPagerState {
        slideItems.size
    }

    HelloTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorScheme.colorScheme.screen.backgroundPrimary),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WelcomeSlideUI(
                slideItems = slideItems,
                pagerState = pagerState
            )
        }
    }
}