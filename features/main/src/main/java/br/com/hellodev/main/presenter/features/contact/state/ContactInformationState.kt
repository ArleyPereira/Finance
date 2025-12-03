package br.com.hellodev.main.presenter.features.contact.state

import br.com.hellodev.core.enums.input.InputType

data class ContactInformationState(
    val isLoading: Boolean = false,
    val phone: String = "",
    val email: String = "",
    val inputError: InputType? = null
)
