package br.com.hellodev.design.presenter.components.section.credit_card

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.com.hellodev.design.presenter.components.card.credit.CreditCardUI
import br.com.hellodev.design.presenter.components.section.header.HeaderMoreSectionUI
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.domain.model.section.credit_card.CreditCardSection

@Composable
fun HorizontalCreditCardSectionUI(
    modifier: Modifier = Modifier,
    section: CreditCardSection? = null,
    onCardClick: (String) -> Unit,
    onAllClick: () -> Unit
) {
    val lazyListState = rememberLazyListState()
    val snapBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        HeaderMoreSectionUI(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            leftTitle = section?.leftTitle.orEmpty(),
            rightTitle = section?.rightTitle.orEmpty(),
            onClick = onAllClick
        )

        LazyRow(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorScheme.colorScheme.screen.backgroundPrimary),
            state = lazyListState,
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            flingBehavior = snapBehavior
        ) {
            items(items = section?.items.orEmpty()) { item ->
                CreditCardUI(
                    modifier = Modifier
                        .fillParentMaxWidth(0.95f),
                    card = item,
                    onClick = { onCardClick(item.id.orEmpty()) }
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun HorizontalCreditCardSectionPreview() {
    HelloTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorScheme.colorScheme.screen.backgroundPrimary),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            HorizontalCreditCardSectionUI(
                section = CreditCardSection.default,
                modifier = Modifier
                    .padding(top = 24.dp),
                onCardClick = {},
                onAllClick = {}
            )
        }
    }
}