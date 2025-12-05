package br.com.hellodev.credit_card.presenter.features.list.viewmodel

import androidx.lifecycle.ViewModel
import br.com.hellodev.credit_card.presenter.features.list.action.ListCreditCardAction
import br.com.hellodev.credit_card.presenter.features.list.state.ListCreditCardState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ListCreditCardViewModel(

) : ViewModel() {

    private var _state = MutableStateFlow(ListCreditCardState())
    var state: StateFlow<ListCreditCardState> = _state

    init {

    }

    fun dispatchAction(action: ListCreditCardAction) {
        when (action) {
            else -> {}
        }
    }

}