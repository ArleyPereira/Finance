package br.com.hellodev.design.presenter.components.indicator

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.ColorScheme

@Composable
fun SlideIndicator(
    modifier: Modifier = Modifier,
    totalIndicators: Int,
    currentIndicator: Int
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(totalIndicators) { index ->
            val isActive = index == currentIndicator

            val width by animateDpAsState(
                targetValue = if (isActive) 32.dp else 8.dp,
                animationSpec = tween(durationMillis = 300), label = ""
            )
            val color by animateColorAsState(
                targetValue = if (isActive) ColorScheme.colorScheme.defaultColor else ColorScheme.colorScheme.greyscale300Color,
                animationSpec = tween(durationMillis = 300), label = ""
            )

            Spacer(
                modifier = Modifier
                    .width(width)
                    .height(8.dp)
                    .clip(CircleShape)
                    .background(color)
            )

            if (index != totalIndicators - 1) {
                Spacer(modifier = Modifier.width(4.dp))
            }
        }
    }
}

@Composable
fun HorizontalPagerIndicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    indicatorShape: Shape = CircleShape,
    indicatorSize: Dp = 8.dp,
    spacing: Dp = 8.dp,
) {
    val spacingPx = with(LocalDensity.current) { spacing.roundToPx() }
    val indicatorSizePx = with(LocalDensity.current) { indicatorSize.roundToPx() }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(spacing),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(pagerState.pageCount) {
                Box(
                    Modifier
                        .size(indicatorSize)
                        .background(
                            color = ColorScheme.colorScheme.greyscale300Color,
                            shape = indicatorShape
                        )
                )
            }
        }

        Box(
            Modifier
                .offset {
                    val scrollPosition = (pagerState.currentPage + pagerState.currentPageOffsetFraction)
                        .coerceIn(0f, (pagerState.pageCount - 1).coerceAtLeast(0).toFloat())
                    IntOffset(
                        x = ((indicatorSizePx + spacingPx) * scrollPosition).toInt(),
                        y = 0
                    )
                }
                .size(indicatorSize)
                .background(
                    color = ColorScheme.colorScheme.defaultColor,
                    shape = indicatorShape
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SlideIndicatorPreview() {
    HelloTheme(isDarkTheme = false) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorScheme.colorScheme.screen.backgroundPrimary),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SlideIndicator(totalIndicators = 3, currentIndicator = 1)
        }
    }
}