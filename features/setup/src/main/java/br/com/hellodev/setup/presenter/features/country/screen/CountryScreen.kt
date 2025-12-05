package br.com.hellodev.setup.presenter.features.country.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.hellodev.core.enums.illustration.IllustrationType
import br.com.hellodev.design.presenter.components.bar.top.TopAppBarUI
import br.com.hellodev.design.presenter.components.button.PrimaryButton
import br.com.hellodev.design.presenter.components.divider.HorizontalDividerUI
import br.com.hellodev.design.presenter.components.icon.default.DefaultIcon
import br.com.hellodev.design.presenter.components.radio.RadioButtonUi
import br.com.hellodev.design.presenter.components.textfield.default.TextFieldUI
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.domain.model.country.Country
import br.com.hellodev.setup.R
import br.com.hellodev.setup.presenter.features.country.action.CountryAction
import br.com.hellodev.setup.presenter.features.country.state.CountryState
import br.com.hellodev.setup.presenter.features.country.viewmodel.CountryViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CountryScreen(
    onBackPressed: () -> Unit
) {
    val viewModel = koinViewModel<CountryViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    CountryContent(
        state = state,
        action = viewModel::dispatchAction,
        onBackPressed = onBackPressed,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CountryContent(
    state: CountryState,
    action: (CountryAction) -> Unit,
    onBackPressed: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarUI(
                title = stringResource(R.string.text_title_country_screen),
                onBackPressed = onBackPressed
            )
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(WindowInsets.navigationBars)
            ) {
                HorizontalDividerUI()

                PrimaryButton(
                    modifier = Modifier
                        .padding(
                            start = 24.dp,
                            end = 24.dp,
                            top = 24.dp,
                            bottom = 32.dp
                        ),
                    text = stringResource(R.string.text_button_select_country_screen),
                    enabled = state.selectedCountry != null,
                    onClick = onBackPressed
                )
            }
        },
        containerColor = ColorScheme.colorScheme.screen.backgroundPrimary,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(
                        horizontal = 24.dp
                    )
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                TextFieldUI(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = state.searchQuery,
                    leadingIcon = {
                        DefaultIcon(type = IllustrationType.IC_SEARCH)
                    },
                    onValueChange = {
                        action(CountryAction.OnSearch(query = it))
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(
                        bottom = 24.dp,
                        top = 8.dp
                    )
                ) {
                    items(state.countriesFiltered) { country ->
                        RadioButtonUi(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = country.name ?: "",
                            selected = country.id == state.selectedCountry?.id,
                            onClick = {
                                action(CountryAction.OnSelect(country = country))
                            }
                        )
                    }
                }
            }
        }
    )
}

@Preview
@Composable
private fun CountryPreview() {
    HelloTheme {
        CountryContent(
            state = CountryState(
                countries = Country.items,
                countriesFiltered = Country.items,
                selectedCountry = Country.items.first()
            ),
            action = {},
            onBackPressed = {},
        )
    }
}