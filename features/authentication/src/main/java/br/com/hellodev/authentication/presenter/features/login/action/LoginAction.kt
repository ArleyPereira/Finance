package br.com.hellodev.authentication.presenter.features.login.action

import br.com.hellodev.core.enums.input.login.LoginInputType

sealed class LoginAction {

    data class OnValueChange(
        val value: String,
        val type: LoginInputType
    ) : LoginAction()

    object OnSignIn : LoginAction()

    object DismissFeedback : LoginAction()

}