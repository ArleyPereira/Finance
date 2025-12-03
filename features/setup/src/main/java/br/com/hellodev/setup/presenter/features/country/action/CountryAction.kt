package br.com.hellodev.setup.presenter.features.country.action

import br.com.hellodev.domain.model.country.Country

sealed class CountryAction {
    data class OnSearch(val query: String): CountryAction()
    data class OnSelect(val country: Country): CountryAction()
}