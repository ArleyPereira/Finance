package br.com.hellodev.profile.presenter.navigation.host

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import br.com.hellodev.profile.presenter.features.edit_profile.screen.EditProfileScreen
import br.com.hellodev.profile.presenter.features.profile.screen.ProfileScreen
import br.com.hellodev.profile.presenter.navigation.routes.ProfileRoutes

fun NavGraphBuilder.profileNavHost(
    navHostController: NavHostController,
    navigateToLoginScreen: () -> Unit,
    paddingValues: PaddingValues = PaddingValues(),
) {
    navigation<ProfileRoutes.Graph>(
        startDestination = ProfileRoutes.Profile
    ) {
        composable<ProfileRoutes.Profile> {
            ProfileScreen(
                paddingValues = paddingValues,
                navigateToEditProfileScreen = {
                    navHostController.navigate(ProfileRoutes.EditProfile)
                }
            )
        }

        composable<ProfileRoutes.EditProfile> {
            EditProfileScreen(
                onBackPressed = navHostController::popBackStack
            )
        }
    }
}