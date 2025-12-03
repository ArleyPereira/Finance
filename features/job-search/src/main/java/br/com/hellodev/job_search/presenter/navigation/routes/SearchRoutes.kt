package br.com.hellodev.job_search.presenter.navigation.routes

import kotlinx.serialization.Serializable

sealed class SearchRoutes {

    @Serializable
    data object Graph: SearchRoutes()

    @Serializable
    data class Search(
        val query: String
    ): SearchRoutes()

}