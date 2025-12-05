package br.com.hellodev.credit_card.presenter.features.form.action

import br.com.hellodev.core.enums.input.credit_card.CreditCardInputType

sealed class FormCreditCardAction {

    object SaveCreditCard : FormCreditCardAction()

    data class OnValueChange(
        val value: String,
        val type: CreditCardInputType
    ) : FormCreditCardAction()

    data class OnLimitChange(
        val value: Float
    ) : FormCreditCardAction()

}