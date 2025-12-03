package br.com.hellodev.job_details.presenter.navigation.routes

import kotlinx.serialization.Serializable

sealed class DetailsRoutes {

    @Serializable
    data object Graph : DetailsRoutes()

    @Serializable
    data class Details(
        val id: Int
    ) : DetailsRoutes()

    @Serializable
    data class Applying(
        val id: Int
    ) : DetailsRoutes()

}