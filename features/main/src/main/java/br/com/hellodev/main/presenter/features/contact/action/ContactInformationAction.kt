package br.com.hellodev.main.presenter.features.contact.action

import br.com.hellodev.core.enums.input.InputType

sealed class ContactInformationAction {
    data object Update : ContactInformationAction()
    data class OnTextFieldChanged(val value: String, val type: InputType) : ContactInformationAction()
}