package br.com.hellodev.domain.model.section.credit_card

import br.com.hellodev.domain.model.credit_card.CreditCard

data class CreditCardSection(
    val id: Int? = null,
    val leftTitle: String? = null,
    val rightTitle: String? = null,
    val items: List<CreditCard>? = null
) {
    companion object {
        val default = CreditCardSection(
            id = 1,
            leftTitle = "Cartões de crédito",
            rightTitle = "Ver todos",
            items = CreditCard.items
        )
    }
}
