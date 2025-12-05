package br.com.hellodev.credit_card.presenter.navigation.host

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import br.com.hellodev.core.extensions.popBackStackSafely
import br.com.hellodev.credit_card.presenter.features.form.screen.FormCreditCardScreen
import br.com.hellodev.credit_card.presenter.features.list.screen.ListCreditCardScreen
import br.com.hellodev.credit_card.presenter.navigation.routes.CreditCardRoutes

fun NavGraphBuilder.creditCardNavHost(navHostController: NavHostController) {
    navigation<CreditCardRoutes.Graph>(
        startDestination = CreditCardRoutes.List
    ) {
        composable<CreditCardRoutes.List> {
            ListCreditCardScreen(
                navigateToFormCreditCardScreen = {
                    navHostController.navigate(CreditCardRoutes.Form())
                },
                onBackPressed = navHostController::popBackStackSafely
            )
        }

        composable<CreditCardRoutes.Form> {
            FormCreditCardScreen(
                onBackPressed = navHostController::popBackStackSafely
            )
        }
    }
}