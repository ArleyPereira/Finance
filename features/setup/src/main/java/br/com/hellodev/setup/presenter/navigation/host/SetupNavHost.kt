package br.com.hellodev.setup.presenter.navigation.host

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import br.com.hellodev.core.extensions.popBackStackSafely
import br.com.hellodev.main.presenter.navigation.host.mainNavHost
import br.com.hellodev.main.presenter.navigation.routes.MainRoutes
import br.com.hellodev.setup.presenter.features.country.screen.CountryScreen
import br.com.hellodev.setup.presenter.features.expertise.screen.ExpertiseScreen
import br.com.hellodev.setup.presenter.navigation.routes.SetupRoutes

fun NavGraphBuilder.setupNavHost(navHostController: NavHostController) {
    navigation<SetupRoutes.Graph>(
        startDestination = SetupRoutes.Expertise
    ) {
        composable<SetupRoutes.Country> {
            CountryScreen(
                onBackPressed = navHostController::popBackStackSafely
            )
        }

        composable<SetupRoutes.Expertise> {
            ExpertiseScreen(
                navigateToProfileScreen = {
                    navHostController.navigate(MainRoutes.Graph)
                },
                onBackPressed = navHostController::popBackStackSafely
            )
        }

        mainNavHost(navHostController = navHostController)
    }
}