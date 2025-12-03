package br.com.hellodev.setup.presenter.navigation.routes

import kotlinx.serialization.Serializable

sealed class SetupRoutes {

    @Serializable
    data object Graph: SetupRoutes()

    @Serializable
    data object Country: SetupRoutes()

    @Serializable
    data object Expertise: SetupRoutes()

}