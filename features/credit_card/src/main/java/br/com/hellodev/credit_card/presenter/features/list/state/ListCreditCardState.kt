package br.com.hellodev.credit_card.presenter.features.list.state

import br.com.hellodev.domain.model.credit_card.CreditCard
import br.com.hellodev.domain.model.feedback.Feedback

data class ListCreditCardState(
    val isLoading: Boolean = false,
    val cards: List<CreditCard> = CreditCard.items,
    val feedback: Feedback? = null
)
