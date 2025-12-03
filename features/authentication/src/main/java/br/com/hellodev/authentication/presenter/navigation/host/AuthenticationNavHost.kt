package br.com.hellodev.authentication.presenter.navigation.host

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import br.com.hellodev.authentication.presenter.features.home.HomeAuthenticationScreen
import br.com.hellodev.authentication.presenter.features.login.screen.LoginScreen
import br.com.hellodev.authentication.presenter.features.signup.screen.SignupScreen
import br.com.hellodev.authentication.presenter.navigation.routes.AuthenticationRoutes
import br.com.hellodev.main.presenter.navigation.host.mainNavHost
import br.com.hellodev.main.presenter.navigation.routes.MainRoutes

fun NavGraphBuilder.authenticationNavHost(navHostController: NavHostController) {
    navigation<AuthenticationRoutes.Graph>(
        startDestination = AuthenticationRoutes.Login
    ) {
        composable<AuthenticationRoutes.Home> {
            HomeAuthenticationScreen(
                navigateToLoginScreen = {
                    navHostController.navigate(AuthenticationRoutes.Login)
                },
                navigateToSignupScreen = {
                    navHostController.navigate(AuthenticationRoutes.Signup)
                }
            )
        }

        composable<AuthenticationRoutes.Login> {
            LoginScreen(
                navigateToMainScreen = {
                    navHostController.navigate(MainRoutes.Main) {
                        popUpTo(AuthenticationRoutes.Graph) {
                            inclusive = true
                        }
                    }
                },
                navigateToSignupScreen = {
                    navHostController.navigate(AuthenticationRoutes.Signup)
                }
            )
        }

        composable<AuthenticationRoutes.Signup> {
            SignupScreen(
                navigateToMainScreen = {
                    navHostController.navigate(MainRoutes.Main) {
                        popUpTo(AuthenticationRoutes.Graph) {
                            inclusive = true
                        }
                    }
                },
                onBackPressed = {
                    navHostController.popBackStack()
                }
            )
        }

        mainNavHost(navHostController = navHostController)
    }
}