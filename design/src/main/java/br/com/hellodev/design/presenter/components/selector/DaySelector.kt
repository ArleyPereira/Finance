package br.com.hellodev.design.presenter.components.selector

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily
import br.com.hellodev.design.presenter.theme.borderStrokeDefault

@Composable
fun DaySelector(
    modifier: Modifier = Modifier,
    selectedDay: Int?,
    onDaySelected: (Int) -> Unit
) {
    val lazyListState = rememberLazyListState()
    val snapBehavior = rememberSnapFlingBehavior(lazyListState)
    val days = (1..31).toList()

    var parentWidth by remember { mutableIntStateOf(0) }
    val itemSize = 64.dp
    val spacing = 12.dp

    val density = LocalDensity.current

    LaunchedEffect(selectedDay, parentWidth) {
        if (selectedDay != null && parentWidth > 0) {
            val index = days.indexOf(selectedDay)
            if (index >= 0) {
                val itemPx = with(density) { itemSize.toPx() }
                val centerOffset = (parentWidth / 2) - (itemPx / 2)
                lazyListState.animateScrollToItem(
                    index = index,
                    scrollOffset = -centerOffset.toInt()
                )
            }
        }
    }

    LazyRow(
        modifier = modifier
            .onGloballyPositioned { parentWidth = it.size.width }
            .fillMaxWidth(),
        state = lazyListState,
        flingBehavior = snapBehavior,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(spacing)
    ) {
        items(days) { day ->
            val isSelected = day == selectedDay

            Card(
                onClick = { onDaySelected(day) },
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isSelected) {
                        ColorScheme.colorScheme.defaultColor
                    } else {
                        ColorScheme.colorScheme.screen.backgroundSecondary
                    }
                ),
                border = borderStrokeDefault(isSelect = isSelected)
            ) {
                Box(
                    modifier = Modifier.size(itemSize),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = day.toString(),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = UrbanistFamily,
                            color = if (isSelected) {
                                Color.White
                            } else {
                                ColorScheme.colorScheme.text.primaryColor
                            }
                        )
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
fun DaySelectorPreview() {
    var selectedDay by remember { mutableIntStateOf(15) }

    HelloTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorScheme.colorScheme.screen.backgroundPrimary),
            verticalArrangement = Arrangement.Bottom
        ) {
            DaySelector(
                selectedDay = selectedDay,
                onDaySelected = { selectedDay = it }
            )
        }
    }
}

