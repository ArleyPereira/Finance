package br.com.hellodev.finance.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import br.com.hellodev.authentication.presenter.navigation.host.authenticationNavHost
import br.com.hellodev.authentication.presenter.navigation.routes.AuthenticationRoutes

@Composable
fun AppNavHost(
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = AuthenticationRoutes.Graph,
        //startDestination = MainRoutes.Graph,
    ) {
        //mainNavHost(navHostController = navHostController)
        //onboardingNavHost(navHostController = navHostController)
        authenticationNavHost(navHostController = navHostController)
    }
}