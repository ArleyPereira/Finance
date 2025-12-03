package br.com.hellodev.design.presenter.components.carousel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.hellodev.domain.model.banner.BannerDomain
import br.com.hellodev.design.R
import br.com.hellodev.design.presenter.components.image.ImageUI
import br.com.hellodev.design.presenter.components.indicator.SlideIndicator
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.ColorScheme

@Composable
fun CarouselUI(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    banners: List<BannerDomain>,
    onClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = modifier
                .fillMaxWidth(),
            pageSpacing = 0.dp,
            contentPadding = PaddingValues(horizontal = 12.dp)
        ) { page ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                ImageUI(
                    imageModel = R.drawable.img_banner,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth(),
                    previewPlaceholder = painterResource(R.drawable.img_banner),
                    shape = RoundedCornerShape(25.dp),
                    onClick = { banners[page].url?.let { onClick(it) } }
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        SlideIndicator(
            totalIndicators = BannerDomain.items.size,
            currentIndicator = pagerState.currentPage
        )
    }
}

@Preview
@Composable
private fun CarouselUIPreview() {
    val pagerState = rememberPagerState { BannerDomain.items.size }

    HelloTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorScheme.colorScheme.screen.backgroundPrimary),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CarouselUI(
                banners = BannerDomain.items,
                pagerState = pagerState,
                onClick = {}
            )
        }
    }
}