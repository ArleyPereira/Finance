package br.com.hellodev.credit_card.presenter.features.form.action

import br.com.hellodev.core.enums.input.credit_card.CreditCardInputType
import br.com.hellodev.core.enums.sheet.SheetType

sealed class FormCreditCardAction {

    object SaveCreditCard : FormCreditCardAction()
    object ClearBottomSheet : FormCreditCardAction()

    data class SetCurrentSheetType(val type: SheetType) : FormCreditCardAction()

    data class OnValueChange(
        val value: String,
        val type: CreditCardInputType
    ) : FormCreditCardAction()

    data class OnLimitChange(
        val value: Float
    ) : FormCreditCardAction()

}