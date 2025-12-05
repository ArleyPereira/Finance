package br.com.hellodev.main.presenter.features.salary.state

import br.com.hellodev.core.enums.input.InputType

data class SalaryExpectationState(
    val isLoading: Boolean = false,
    val minimumSalary: Float = 0f,
    val maximumSalary: Float = 0f,
    val currency: String = "",
    val frequency: String = "",
    val inputError: InputType? = null,
    val frequencies: List<String> = emptyList()
)
