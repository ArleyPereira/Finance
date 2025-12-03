package br.com.hellodev.onboarding.presenter.navigation.host

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import br.com.hellodev.authentication.presenter.navigation.host.authenticationNavHost
import br.com.hellodev.authentication.presenter.navigation.routes.AuthenticationRoutes
import br.com.hellodev.onboarding.presenter.navigation.routes.OnboardingRoutes
import br.com.hellodev.onboarding.presenter.features.onboarding.screen.WelcomeScreen

fun NavGraphBuilder.onboardingNavHost(
    navHostController: NavHostController
) {
    navigation<OnboardingRoutes.Graph>(
        startDestination = OnboardingRoutes.Welcome
    ) {
        composable<OnboardingRoutes.Welcome> {
            WelcomeScreen(
                navigateToAuthentication = {
                    navHostController.navigate(AuthenticationRoutes.Graph) {
                        popUpTo(OnboardingRoutes.Graph) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }

    authenticationNavHost(navHostController = navHostController)
}