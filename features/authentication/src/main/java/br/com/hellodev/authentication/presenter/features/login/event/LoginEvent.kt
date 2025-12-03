package br.com.hellodev.authentication.presenter.features.login.event

sealed class LoginEvent {
    object Idle : LoginEvent()

    sealed class Navigation {
        object Main : LoginEvent()
    }
}