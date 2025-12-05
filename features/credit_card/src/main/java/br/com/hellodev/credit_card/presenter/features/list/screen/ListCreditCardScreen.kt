package br.com.hellodev.credit_card.presenter.features.list.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_ADD_CARD_LINE
import br.com.hellodev.credit_card.presenter.features.list.action.ListCreditCardAction
import br.com.hellodev.credit_card.presenter.features.list.state.ListCreditCardState
import br.com.hellodev.credit_card.presenter.features.list.viewmodel.ListCreditCardViewModel
import br.com.hellodev.design.extensions.modifier.shimmerPlaceholder
import br.com.hellodev.design.presenter.components.bar.top.AnimatedActionsBar
import br.com.hellodev.design.presenter.components.bar.top.TopAppBarUI
import br.com.hellodev.design.presenter.components.bottom.screen.BottomScreenUI
import br.com.hellodev.design.presenter.components.card.credit.CreditCardUI
import br.com.hellodev.design.presenter.components.icon.default.getDrawableIllustration
import br.com.hellodev.design.presenter.components.snackbar.FeedbackUI
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.domain.model.credit_card.CreditCard
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ListCreditCardScreen(
    navigateToFormCreditCardScreen: () -> Unit,
    onBackPressed: () -> Unit
) {
    val viewModel = koinViewModel<ListCreditCardViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    ListCreditCardContent(
        state = state,
        action = viewModel::dispatchAction,
        navigateToFormCreditCardScreen = navigateToFormCreditCardScreen,
        onBackPressed = onBackPressed
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListCreditCardContent(
    state: ListCreditCardState,
    action: (ListCreditCardAction) -> Unit,
    navigateToFormCreditCardScreen: () -> Unit,
    onBackPressed: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarUI(
                title = "Cartões de crédito",
                actions = {
                    AnimatedActionsBar(
                        isVisible = state.isLoading,
                        content = {
                            IconButton(
                                onClick = navigateToFormCreditCardScreen,
                                content = {
                                    Icon(
                                        painter = getDrawableIllustration(IC_ADD_CARD_LINE),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(24.dp)
                                            .shimmerPlaceholder(visible = state.isLoading),
                                        tint = ColorScheme.colorScheme.icon.color
                                    )
                                }
                            )
                        }
                    )
                },
                onBackPressed = onBackPressed
            )
        },
        bottomBar = {
            BottomScreenUI(
                isVisible = state.isLoading,
                feedback = {
                    state.feedback?.let { feedback ->
                        if (feedback.show) {
                            FeedbackUI(
                                feedback = feedback,
                                onDismiss = { action(ListCreditCardAction.DismissFeedback) }
                            )
                        }
                    }
                },
                content = {}
            )
        },
        containerColor = ColorScheme.colorScheme.screen.backgroundPrimary,
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .background(ColorScheme.colorScheme.screen.backgroundPrimary),
                contentPadding = PaddingValues(
                    top = paddingValues.calculateTopPadding() + 16.dp,
                    bottom = paddingValues.calculateBottomPadding() + 16.dp
                ),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(CreditCard.items) { item ->
                    CreditCardUI(
                        card = item,
                        isLoading = state.isLoading,
                        onClick = {}
                    )
                }
            }
        }
    )
}

@PreviewLightDark
@Composable
private fun ListCreditCardPreview() {
    HelloTheme {
        ListCreditCardContent(
            state = ListCreditCardState(
                isLoading = false
            ),
            action = {},
            navigateToFormCreditCardScreen = {},
            onBackPressed = {}
        )
    }
}