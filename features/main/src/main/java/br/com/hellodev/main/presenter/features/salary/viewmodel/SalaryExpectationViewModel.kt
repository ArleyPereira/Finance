package br.com.hellodev.main.presenter.features.salary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hellodev.core.enums.input.InputType
import br.com.hellodev.core.enums.input.InputType.SALARY_CURRENCY
import br.com.hellodev.core.enums.input.InputType.SALARY_FREQUENCY
import br.com.hellodev.main.presenter.features.salary.action.SalaryExpectationAction
import br.com.hellodev.main.presenter.features.salary.state.SalaryExpectationState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SalaryExpectationViewModel(

) : ViewModel() {

    private var _state = MutableStateFlow(SalaryExpectationState())
    var state: StateFlow<SalaryExpectationState> = _state

    init {
        getSaveSalaryExpectation()
    }

    fun dispatchAction(action: SalaryExpectationAction) {
        when (action) {
            is SalaryExpectationAction.Save -> {
                saveSalaryExpectation()
            }

            is SalaryExpectationAction.OnTextFieldChanged -> {
                onTextFieldChanged(action.value, action.type)
            }
        }
    }

    private fun getSaveSalaryExpectation() {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(frequencies = getFrequencies())
            }
        }
    }

    private fun saveSalaryExpectation() {
        viewModelScope.launch {
            if (!isValidData()) {
                inputFeedbackError()
                return@launch
            }

            // Envia os dados do perfil para algum lugar
        }
    }

    private fun onTextFieldChanged(value: String, type: InputType) {
        clearError()

//        _state.update { currentState ->
//            when (type) {
//                SALARY_MINIMUM -> currentState.copy(minimumSalary = value)
//                SALARY_MAXIMUM -> currentState.copy(maximumSalary = value)
//                SALARY_CURRENCY -> currentState.copy(currency = value)
//                SALARY_FREQUENCY -> currentState.copy(frequency = value)
//                else -> currentState
//            }
//        }
    }

    private fun inputFeedbackError() {
        val inputError = when {
            //_state.value.minimumSalary.isEmpty() -> SALARY_MINIMUM
            _state.value.currency.isEmpty() -> SALARY_CURRENCY
            _state.value.frequency.isEmpty() -> SALARY_FREQUENCY
            else -> null
        }

        _state.update { currentState ->
            currentState.copy(inputError = inputError)
        }
    }

    private fun isValidData(): Boolean {
        //val minimumSalary = _state.value.minimumSalary.isNotEmpty()
        val currency = _state.value.currency.isNotEmpty()
        val frequency = _state.value.frequency.isNotEmpty()

        return currency && frequency
    }

    private fun getFrequencies(): List<String> {
        return listOf(
            "Mensal",
            "Semestral",
            "Anual"
        )
    }

    private fun clearError() {
        _state.update { currentState ->
            currentState.copy(inputError = null)
        }
    }

}