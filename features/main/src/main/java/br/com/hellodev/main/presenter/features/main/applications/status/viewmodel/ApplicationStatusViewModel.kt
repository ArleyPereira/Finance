package br.com.hellodev.main.presenter.features.applications.status.viewmodel

import androidx.lifecycle.ViewModel
import br.com.hellodev.domain.model.job.item.JobItemDomain
import br.com.hellodev.main.presenter.features.applications.status.action.ApplicationStatusAction
import br.com.hellodev.main.presenter.features.applications.status.state.ApplicationStatusState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ApplicationStatusViewModel(

) : ViewModel() {

    private var _state = MutableStateFlow(ApplicationStatusState())
    var state: StateFlow<ApplicationStatusState> = _state

    init {
        _state.value = _state.value.copy(
            isLoading = false,
            job = JobItemDomain.items.random()
        )
    }

    fun dispatchAction(action: ApplicationStatusAction) {
        when (action) {
            else -> {}
        }
    }

}