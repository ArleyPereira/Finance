package br.com.hellodev.credit_card.presenter.navigation.routes

import kotlinx.serialization.Serializable

sealed class CreditCardRoutes {

    @Serializable
    data object Graph : CreditCardRoutes()

    @Serializable
    data object List : CreditCardRoutes()

    @Serializable
    data class Form(
        val id: String? = null
    ) : CreditCardRoutes()

}