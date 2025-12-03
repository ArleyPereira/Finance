package br.com.hellodev.setup.presenter.features.country.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hellodev.domain.model.country.Country
import br.com.hellodev.setup.presenter.features.country.action.CountryAction
import br.com.hellodev.setup.presenter.features.country.state.CountryState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CountryViewModel : ViewModel() {

    private var _state = MutableStateFlow(CountryState())
    var state: StateFlow<CountryState> = _state

    init {
        getCountries()
    }

    fun dispatchAction(action: CountryAction) {
        when (action) {
            is CountryAction.OnSearch -> {
                onSearch(query = action.query)
            }

            is CountryAction.OnSelect -> {
                onSelect(country = action.country)
            }
        }
    }

    private fun getCountries() {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(
                    isLoading = false,
                    countries = Country.items,
                    countriesFiltered = Country.items
                )
            }
        }
    }

    private fun onSearch(query: String) {
        val filteredCountries = _state.value.countries.filter { country ->
            country.name?.contains(
                query,
                ignoreCase = true
            ) == true
        }

        _state.update { currentState ->
            currentState.copy(
                searchQuery = query,
                countriesFiltered = filteredCountries
            )
        }
    }

    private fun onSelect(country: Country) {
        _state.update { currentState ->
            currentState.copy(
                selectedCountry = country
            )
        }
    }

}