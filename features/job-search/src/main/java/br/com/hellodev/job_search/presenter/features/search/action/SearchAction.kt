package br.com.hellodev.job_search.presenter.features.search.action

sealed class SearchAction {
    data object OnSearch : SearchAction()
    data class OnSearchChange(val query: String) : SearchAction()
}