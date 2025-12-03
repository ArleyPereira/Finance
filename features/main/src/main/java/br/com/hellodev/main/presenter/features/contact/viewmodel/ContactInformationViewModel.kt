package br.com.hellodev.main.presenter.features.contact.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hellodev.core.enums.input.InputType
import br.com.hellodev.core.enums.input.InputType.EMAIL
import br.com.hellodev.core.enums.input.InputType.PHONE
import br.com.hellodev.core.functions.isValidEmail
import br.com.hellodev.core.functions.isValidPhone
import br.com.hellodev.domain.model.user.User
import br.com.hellodev.main.presenter.features.contact.action.ContactInformationAction
import br.com.hellodev.main.presenter.features.contact.state.ContactInformationState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ContactInformationViewModel : ViewModel() {

    private var _state = MutableStateFlow(ContactInformationState())
    var state: StateFlow<ContactInformationState> = _state

    init {
        initData()
    }

    fun dispatchAction(action: ContactInformationAction) {
        when (action) {
            is ContactInformationAction.OnTextFieldChanged -> {
                onTextFieldChanged(value = action.value, type = action.type)
            }

            is ContactInformationAction.Update -> {
                update()
            }
        }
    }

    private fun initData() {
        _state.update { currentState ->
            currentState.copy(
                phone = User.userDefault.phone ?: "",
                email = User.userDefault.email ?: ""
            )
        }
    }

    private fun update() {
        viewModelScope.launch {
            if (!isValidProfile()) {
                inputFeedbackError()
                return@launch
            }
            // Envia os dados do perfil para algum lugar
        }
    }

    private fun onTextFieldChanged(value: String, type: InputType) {
        clearError()

        _state.update { currentState ->
            when (type) {
                PHONE -> currentState.copy(phone = value)
                EMAIL -> currentState.copy(email = value.lowercase())
                else -> currentState
            }
        }
    }

    private fun inputFeedbackError() {
        val inputError = when {
            !isValidPhone(_state.value.phone) -> PHONE
            !isValidEmail(_state.value.email) -> EMAIL
            else -> null
        }

        _state.update { currentState ->
            currentState.copy(inputError = inputError)
        }
    }

    private fun isValidProfile(): Boolean {
        val phone = isValidPhone(_state.value.phone)
        val email = isValidEmail(_state.value.email)

        return phone && email
    }

    private fun clearError() {
        _state.update { currentState ->
            currentState.copy(inputError = null)
        }
    }

}