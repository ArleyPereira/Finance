package br.com.hellodev.main.presenter.navigation.host

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import br.com.hellodev.main.presenter.navigation.main.screen.MainScreen
import br.com.hellodev.main.presenter.navigation.routes.MainRoutes

fun NavGraphBuilder.mainNavHost(
    navHostController: NavHostController
) {
    navigation<MainRoutes.Graph>(
        startDestination = MainRoutes.Main
    ) {
        composable<MainRoutes.Main> {
            MainScreen()
        }
    }
}