package br.com.hellodev.job_details.presenter.features.details.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import br.com.hellodev.domain.model.job.item.JobItemDomain
import br.com.hellodev.job_details.presenter.features.details.action.DetailsAction
import br.com.hellodev.job_details.presenter.features.details.state.DetailsState
import br.com.hellodev.job_details.presenter.navigation.routes.DetailsRoutes
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _state = MutableStateFlow(DetailsState())
    var state: StateFlow<DetailsState> = _state

    private val jobId = savedStateHandle.toRoute<DetailsRoutes.Details>().id

    init {
        getJobDetails()
    }

    fun dispatchAction(action: DetailsAction) {
        when (action) {
            is DetailsAction.Apply -> {
                jobApply()
            }
        }
    }

    private fun getJobDetails() {
        viewModelScope.launch {
            delay(2000)

            _state.value = _state.value.copy(
                isLoading = false,
                job = JobItemDomain.items.find { it.id == jobId }
            )
        }
    }

    private fun jobApply() {
        viewModelScope.launch {}
    }

}