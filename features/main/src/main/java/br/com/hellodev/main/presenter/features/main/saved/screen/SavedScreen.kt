package br.com.hellodev.main.presenter.features.main.saved.screen

import android.annotation.SuppressLint
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
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
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
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_CLOSE
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_FILTER
import br.com.hellodev.design.presenter.components.bar.search.SearchBarUI
import br.com.hellodev.design.presenter.components.bottom.sheet.content.saved.SavedJobSheetContent
import br.com.hellodev.design.presenter.components.bottom.sheet.default.DefaultBottomSheet
import br.com.hellodev.design.presenter.components.empty.EmptyUI
import br.com.hellodev.design.presenter.components.icon.default.DefaultIcon
import br.com.hellodev.design.presenter.components.item.job.item.JobItemUI
import br.com.hellodev.design.presenter.components.loading.CircularLoadingScreen
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.domain.model.job.item.JobItemDomain
import br.com.hellodev.main.presenter.features.main.saved.action.SavedAction
import br.com.hellodev.main.presenter.features.main.saved.state.SavedState
import br.com.hellodev.main.presenter.features.main.saved.viewmodel.SavedViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun SavedScreen(
    navigateToDetails: (Int) -> Unit,
) {
    val viewModel = koinViewModel<SavedViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    SavedContent(
        state = state,
        action = viewModel::dispatchAction,
        navigateToDetails = navigateToDetails
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SavedContent(
    state: SavedState,
    action: (SavedAction) -> Unit,
    navigateToDetails: (Int) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    var showBottomSheet by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
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
                                    onClick = { action(SavedAction.OnClearSearch) }
                                )
                            },
                            onValueChange = {
                                action(SavedAction.OnSearchChange(it))
                            },
                            onSearchAction = {
                                action(SavedAction.OnSearch)
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
                                items(
                                    items = state.items ?: emptyList(),
                                    key = { it.id ?: 0 }
                                ) { job ->
                                    JobItemUI(
                                        modifier = Modifier
                                            .animateItem()
                                            .padding(horizontal = 24.dp),
                                        job = job,
                                        isSaved = true,
                                        onSaveClick = {
                                            showBottomSheet = true
                                            action(SavedAction.OnJobSelected(job))
                                        },
                                        onClick = { navigateToDetails(job.id ?: 0) }
                                    )
                                }
                            }
                        }
                    }
                }

                if (showBottomSheet && state.jobSelected != null) {
                    DefaultBottomSheet(
                        onDismissRequest = {
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                showBottomSheet = false
                                action(SavedAction.OnJobSelected(null))
                            }
                        },
                        sheetState = sheetState,
                        content = {
                            SavedJobSheetContent(
                                job = state.jobSelected,
                                onJobClick = { id ->
                                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                                        showBottomSheet = false
                                        navigateToDetails(id)
                                    }
                                },
                                onCancelClick = {
                                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                                        showBottomSheet = false
                                        action(SavedAction.OnJobSelected(null))
                                    }
                                },
                                onConfirmClick = {
                                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                                        showBottomSheet = false
                                        action(SavedAction.OnRemoveJob)
                                    }
                                }
                            )
                        }
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun SavedPreview() {
    HelloTheme {
        SavedContent(
            state = SavedState(
                items = JobItemDomain.items,
                isScreenLoading = false
            ),
            action = {},
            navigateToDetails = {}
        )
    }
}