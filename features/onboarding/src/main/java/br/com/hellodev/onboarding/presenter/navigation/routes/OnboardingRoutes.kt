package br.com.hellodev.onboarding.presenter.navigation.routes

import kotlinx.serialization.Serializable

sealed class OnboardingRoutes {

    @Serializable
    data object Graph: OnboardingRoutes()

    @Serializable
    data object Welcome: OnboardingRoutes()

}