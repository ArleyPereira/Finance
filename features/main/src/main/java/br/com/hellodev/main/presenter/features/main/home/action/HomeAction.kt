package br.com.hellodev.main.presenter.features.home.action

import br.com.hellodev.domain.model.category.CategoryDomain

sealed class HomeAction {
    data object OnSearch: HomeAction()
    data class OnSearchChange(val query: String): HomeAction()
    data class OnCategorySelected(val category: CategoryDomain?): HomeAction()
}