package br.com.hellodev.credit_card.presenter.features.form.state

import br.com.hellodev.core.enums.input.credit_card.CreditCardInputType
import br.com.hellodev.domain.model.feedback.Feedback

data class FormCreditCardState(
    val isLoading: Boolean = false,
    val name: String = "",
    val limit: Float = 0f,
    val closingDay: String = "",
    val dueDay: String = "",
    val inputError: CreditCardInputType? = null,
    val feedback: Feedback? = null
)
