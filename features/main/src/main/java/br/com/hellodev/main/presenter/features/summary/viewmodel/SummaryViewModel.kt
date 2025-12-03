package br.com.hellodev.main.presenter.features.summary.viewmodel

import androidx.lifecycle.ViewModel
import br.com.hellodev.main.presenter.features.summary.action.SummaryAction
import br.com.hellodev.main.presenter.features.summary.state.SummaryState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SummaryViewModel(

) : ViewModel() {

    private var _state = MutableStateFlow(SummaryState())
    var state: StateFlow<SummaryState> = _state

    init {

    }

    fun dispatchAction(action: SummaryAction) {
        when (action) {
            is SummaryAction.Save -> {

            }
        }
    }

    private fun save() {

    }

}