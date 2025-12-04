package br.com.hellodev.profile.presenter.navigation.routes

import kotlinx.serialization.Serializable

sealed class ProfileRoutes {

    @Serializable
    object Graph : ProfileRoutes()

    @Serializable
    object Profile : ProfileRoutes()

    @Serializable
    object EditProfile: ProfileRoutes()

}