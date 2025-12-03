package br.com.hellodev.main.presenter.features.main.saved.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hellodev.domain.model.job.item.JobItemDomain
import br.com.hellodev.main.presenter.features.main.saved.action.SavedAction
import br.com.hellodev.main.presenter.features.main.saved.state.SavedState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SavedViewModel(

) : ViewModel() {

    private var _state = MutableStateFlow(SavedState())
    var state: StateFlow<SavedState> = _state

    init {
        getJobsSaved()
    }

    fun dispatchAction(action: SavedAction) {
        when (action) {
            is SavedAction.OnSearch -> {
                onSearch()
            }

            is SavedAction.OnSearchChange -> {
                onSearchChange(query = action.query)
            }

            is SavedAction.OnJobSelected -> {
                onJobSelected(job = action.job)
            }

            is SavedAction.OnRemoveJob -> {
                onRemoveJob()
            }

            is SavedAction.OnClearSearch -> {
                onClearSearch()
            }
        }
    }

    private fun getJobsSaved() {
        viewModelScope.launch {
            delay(2000)
            _state.update { currentState ->
                currentState.copy(
                    items = JobItemDomain.items,
                    isScreenLoading = false
                )
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

    private fun onSearchChange(query: String) {
        _state.update { currentState ->
            currentState.copy(query = query)
        }
    }

    private fun onJobSelected(job: JobItemDomain?) {
        _state.update { currentState ->
            currentState.copy(jobSelected = job)
        }
    }

    private fun onRemoveJob() {
        val currentJobs = _state.value.items?.toMutableList() ?: mutableListOf()
        currentJobs.removeIf { it.id == _state.value.jobSelected?.id }

        _state.update { currentState ->
            currentState.copy(
                items = currentJobs,
                jobSelected = null
            )
        }
    }

}
