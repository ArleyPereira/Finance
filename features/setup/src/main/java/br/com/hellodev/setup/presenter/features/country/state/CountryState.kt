package br.com.hellodev.setup.presenter.features.country.state

import br.com.hellodev.domain.model.country.Country

data class CountryState(
    val isLoading: Boolean = true,
    val searchQuery: String = "",
    val selectedCountry: Country? = null,
    val countries: List<Country> = emptyList(),
    val countriesFiltered: List<Country> = emptyList()
)
