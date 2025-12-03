package br.com.hellodev.main.presenter.features.applications.list.screen

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
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.hellodev.domain.model.job.item.JobItemDomain
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_CLOSE
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_FILTER
import br.com.hellodev.design.presenter.components.bar.search.SearchBarUI
import br.com.hellodev.design.presenter.components.divider.HorizontalDividerUI
import br.com.hellodev.design.presenter.components.empty.EmptyUI
import br.com.hellodev.design.presenter.components.icon.default.DefaultIcon
import br.com.hellodev.design.presenter.components.item.job.application.JobApplicationItemUI
import br.com.hellodev.design.presenter.components.loading.CircularLoadingScreen
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.main.presenter.features.applications.list.action.ApplicationListAction
import br.com.hellodev.main.presenter.features.applications.list.state.ApplicationListState
import br.com.hellodev.main.presenter.features.applications.list.viewmodel.ApplicationListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ApplicationListScreen(
    navigateToApplicationStatusScreen: () -> Unit
) {
    val viewModel = koinViewModel<ApplicationListViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    ApplicationListContent(
        state = state,
        action = viewModel::dispatchAction,
        navigateToApplicationStatusScreen = navigateToApplicationStatusScreen
    )
}

@Composable
fun ApplicationListContent(
    state: ApplicationListState,
    action: (ApplicationListAction) -> Unit,
    navigateToApplicationStatusScreen: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorScheme.colorScheme.screen.backgroundPrimary)
    ) {
        when {
            state.isScreenLoading -> {
                CircularLoadingScreen()
            }

            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(WindowInsets.statusBars)
                        .padding(top = 16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(start = 24.dp, end = 12.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        SearchBarUI(
                            modifier = Modifier
                                .weight(1f),
                            value = state.query,
                            placeholder = "Pesquise",
                            trailingIcon = {
                                DefaultIcon(
                                    type = IC_CLOSE,
                                    tint = ColorScheme.colorScheme.textField.placeholder,
                                    onClick = { action(ApplicationListAction.OnClearSearch) }
                                )
                            },
                            onValueChange = {
                                action(ApplicationListAction.OnSearchChange(it))
                            },
                            onSearchAction = {
                                action(ApplicationListAction.OnSearch)
                            }
                        )

                        IconButton(
                            onClick = {},
                            content = {
                                DefaultIcon(type = IC_FILTER)
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    when {
                        state.isSearchLoading -> {
                            CircularLoadingScreen()
                        }

                        state.items?.isEmpty() == true -> {
                            EmptyUI(
                                modifier = Modifier
                                    .fillMaxSize(),
                                title = "Não encontrado",
                                description = "Desculpe, a palavra-chave que você digitou não pode ser encontrada. Verifique novamente ou pesquise com outra palavra-chave."
                            )
                        }

                        else -> {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentPadding = PaddingValues(
                                    bottom = WindowInsets.navigationBars.asPaddingValues()
                                        .calculateBottomPadding() * 3
                                ),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                itemsIndexed(
                                    items = state.items ?: emptyList(),
                                    key = { _, item -> item.id ?: 0 }
                                ) { index, job ->
                                    JobApplicationItemUI(
                                        job = job,
                                        modifier = Modifier
                                            .padding(
                                                start = 24.dp,
                                                end = 24.dp,
                                                bottom = 16.dp
                                            ),
                                        onClick = navigateToApplicationStatusScreen
                                    )

                                    if (index < (state.items?.size ?: 0) - 1) {
                                        HorizontalDividerUI(
                                            modifier = Modifier
                                                .padding(horizontal = 24.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun ApplicationListPreview() {
    HelloTheme {
        ApplicationListContent(
            state = ApplicationListState(
                isScreenLoading = false,
                items = JobItemDomain.items
            ),
            action = {},
            navigateToApplicationStatusScreen = {}
        )
    }
}