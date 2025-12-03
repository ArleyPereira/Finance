package br.com.hellodev.main.presenter.features.applications.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hellodev.domain.model.job.item.JobItemDomain
import br.com.hellodev.main.presenter.features.applications.list.action.ApplicationListAction
import br.com.hellodev.main.presenter.features.applications.list.state.ApplicationListState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ApplicationListViewModel(

) : ViewModel() {

    private var _state = MutableStateFlow(ApplicationListState())
    var state: StateFlow<ApplicationListState> = _state

    init {
        viewModelScope.launch {
            delay(2000)

            _state.update { currentState ->
                currentState.copy(
                    isScreenLoading = false,
                    items = JobItemDomain.items
                )
            }
        }
    }

    fun dispatchAction(action: ApplicationListAction) {
        when (action) {
            is ApplicationListAction.OnClearSearch -> {
                onClearSearch()
            }

            is ApplicationListAction.OnSearch -> {
                onSearch()
            }

            is ApplicationListAction.OnSearchChange -> {
                onSearchChange(query = action.query)
            }
        }
    }

    private fun onSearch() {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(isSearchLoading = true)
            }

            delay(2000)

            val query = _state.value.query
            val filteredJobs = JobItemDomain.items.filter {
                it.title?.contains(query, true) == true
            }

            _state.update { currentState ->
                currentState.copy(
                    isSearchLoading = false,
                    items = filteredJobs
                )
            }
        }
    }

    private fun onSearchChange(query: String) {
        _state.update { currentState ->
            currentState.copy(query = query)
        }
    }

    private fun onClearSearch() {
        viewModelScope.launch {
            if (_state.value.query.isNotEmpty() && _state.value.query.isNotBlank()) {
                _state.update { currentState ->
                    currentState.copy(
                        isSearchLoading = true,
                        query = "",
                    )
                }

                delay(2000)

                _state.update { currentState ->
                    currentState.copy(
                        items = JobItemDomain.items,
                        isSearchLoading = false
                    )
                }
            }
        }
    }

}