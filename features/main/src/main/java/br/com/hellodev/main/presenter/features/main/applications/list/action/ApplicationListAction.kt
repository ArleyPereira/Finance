package br.com.hellodev.main.presenter.features.applications.list.action

sealed class ApplicationListAction {
    data object OnSearch : ApplicationListAction()
    data object OnClearSearch : ApplicationListAction()
    data class OnSearchChange(val query: String) : ApplicationListAction()

}