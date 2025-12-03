package br.com.hellodev.main.presenter.navigation.routes

import kotlinx.serialization.Serializable

sealed class BottomBarRoutes {

    @Serializable
    data object Home : BottomBarRoutes()

    @Serializable
    data object Saved : BottomBarRoutes()

    @Serializable
    data object ApplicationList : BottomBarRoutes()

    @Serializable
    data object Message : BottomBarRoutes()

    @Serializable
    data object Account : BottomBarRoutes()

    @Serializable
    data class ApplicationStatus(
        val id: Int
    ) : BottomBarRoutes()

    @Serializable
    data object ContactInformation : BottomBarRoutes()

    @Serializable
    data object Summary : BottomBarRoutes()

    @Serializable
    data object SalaryExpectation : BottomBarRoutes()

    @Serializable
    data object Profile : BottomBarRoutes()

}