package br.com.hellodev.authentication.presenter.features.signup.event

sealed class SignupEvent {
    object Idle : SignupEvent()

    sealed class Navigation {
        object Main : SignupEvent()
    }
}