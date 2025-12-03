package br.com.hellodev.main.presenter.navigation.main.items

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import br.com.hellodev.main.R
import br.com.hellodev.main.presenter.navigation.routes.BottomBarRoutes

sealed class BottomAppBarItems(
    val route: BottomBarRoutes,
    @StringRes val label: Int,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int
) {

    data object Home : BottomAppBarItems(
        route = BottomBarRoutes.Home,
        label = R.string.label_home_bottom_app_bar,
        selectedIcon = br.com.hellodev.design.R.drawable.ic_home_fill,
        unselectedIcon = br.com.hellodev.design.R.drawable.ic_home_line
    )

    data object Saved : BottomAppBarItems(
        route = BottomBarRoutes.Saved,
        label = R.string.label_saved_bottom_app_bar,
        selectedIcon = br.com.hellodev.design.R.drawable.ic_saved_fill,
        unselectedIcon = br.com.hellodev.design.R.drawable.ic_saved_line
    )

    data object Application : BottomAppBarItems(
        route = BottomBarRoutes.ApplicationList,
        label = R.string.label_application_bottom_app_bar,
        selectedIcon = br.com.hellodev.design.R.drawable.ic_application_fill,
        unselectedIcon = br.com.hellodev.design.R.drawable.ic_application_line
    )

    data object Message : BottomAppBarItems(
        route = BottomBarRoutes.Message,
        label = R.string.label_message_bottom_app_bar,
        selectedIcon = br.com.hellodev.design.R.drawable.ic_message_fill,
        unselectedIcon = br.com.hellodev.design.R.drawable.ic_message_line
    )

    data object Account : BottomAppBarItems(
        route = BottomBarRoutes.Account,
        label = R.string.label_profile_bottom_app_bar,
        selectedIcon = br.com.hellodev.design.R.drawable.ic_person_fill,
        unselectedIcon = br.com.hellodev.design.R.drawable.ic_person_line
    )

    companion object {
        val items = listOf(Home, Saved, Application, Message, Account)
    }

}