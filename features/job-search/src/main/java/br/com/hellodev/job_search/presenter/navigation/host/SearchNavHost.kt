package br.com.hellodev.job_search.presenter.navigation.host

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import br.com.hellodev.core.extensions.popBackStackSafely
import br.com.hellodev.job_details.presenter.navigation.host.detailsNavHost
import br.com.hellodev.job_details.presenter.navigation.routes.DetailsRoutes
import br.com.hellodev.job_search.presenter.features.search.screen.SearchScreen
import br.com.hellodev.job_search.presenter.navigation.routes.SearchRoutes

fun NavGraphBuilder.searchNavHost(
    navHostController: NavHostController
) {
    navigation<SearchRoutes.Graph>(
        startDestination = SearchRoutes.Search(query = "")
    ) {
        composable<SearchRoutes.Search> {
            SearchScreen(
                navigateToDetails = {
                    navHostController.navigate(DetailsRoutes.Details(id = it))
                },
                onBackPressed = navHostController::popBackStackSafely
            )
        }

        detailsNavHost(navHostController = navHostController)
    }
}