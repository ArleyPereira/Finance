package br.com.hellodev.main.presenter.features.salary.action

import br.com.hellodev.core.enums.input.InputType

sealed class SalaryExpectationAction {
    data object Save : SalaryExpectationAction()
    data class OnTextFieldChanged(val value: String, val type: InputType) : SalaryExpectationAction()
}