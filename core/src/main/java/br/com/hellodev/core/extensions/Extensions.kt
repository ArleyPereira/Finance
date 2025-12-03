package br.com.hellodev.core.extensions

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController

fun NavHostController.popBackStackSafely() {
    if (currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED) popBackStack()
}