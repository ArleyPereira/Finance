package br.com.hellodev.job_search.presenter.features.search.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import br.com.hellodev.domain.model.category.CategoryDomain
import br.com.hellodev.domain.model.job.item.JobItemDomain
import br.com.hellodev.job_search.presenter.features.search.action.SearchAction
import br.com.hellodev.job_search.presenter.features.search.state.SearchState
import br.com.hellodev.job_search.presenter.navigation.routes.SearchRoutes
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _state = MutableStateFlow(SearchState())
    var state: StateFlow<SearchState> = _state

    private val query = savedStateHandle.toRoute<SearchRoutes.Search>().query

    init {
        initData()
    }

    fun dispatchAction(action: SearchAction) {
        when (action) {
            is SearchAction.OnSearchChange -> {
                onSearchChange(query = action.query)
            }

            is SearchAction.OnSearch -> {
                onSearch()
            }
        }
    }

    private fun initData() {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(query = query)
            }

            val filteredJobs = JobItemDomain.items.filter {
                it.title?.contains(query, true) == true
            }

            delay(2000)

            _state.update { currentState ->
                currentState.copy(
                    isLoading = false,
                    query = query,
                    jobs = filteredJobs
                )
            }
        }
    }

    private fun onSearchChange(query: String) {
        _state.update { currentState ->
            currentState.copy(query = query)
        }
    }

    private fun onSearch() {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(isLoading = true)
            }

            delay(2000)

            val query = _state.value.query
            val filteredJobs = JobItemDomain.items.filter {
                it.title?.contains(query, true) == true
            }

            _state.update { currentState ->
                currentState.copy(
                    isLoading = false,
                    jobs = filteredJobs
                )
            }
        }
    }

    private fun onCategorySelected(category: CategoryDomain?) {
//        val jobs = when (category) {
//            CategoryDomain.items[1] -> JobItemDomain.designJobs
//            CategoryDomain.items[2] -> JobItemDomain.technologyJobs
//            CategoryDomain.items[3] -> JobItemDomain.financeJobs
//            CategoryDomain.items[4] -> JobItemDomain.educationJobs
//            CategoryDomain.items[5] -> JobItemDomain.healthJobs
//            CategoryDomain.items[6] -> JobItemDomain.marketingJobs
//            CategoryDomain.items[7] -> JobItemDomain.salesJobs
//            CategoryDomain.items[8] -> JobItemDomain.engineeringJobs
//            CategoryDomain.items[9] -> JobItemDomain.logisticsJobs
//            else -> JobItemDomain.items
//        }
//
//        val query = _state.value.query
//        val jobsFiltered = jobs?.filter { it.title?.contains(query, true) == true }
//
//        val recent = JobSectionDomain.items[1].copy(items = jobsFiltered)
//
//        _state.update { currentState ->
//            currentState.copy(
//                categorySelected = category,
//                recent = recent
//            )
//        }
    }

}