package br.com.hellodev.authentication.presenter.features.signup.action

import br.com.hellodev.core.enums.input.signup.SignupInputType

sealed class SignupAction {

    data class OnValueChange(
        val value: String,
        val type: SignupInputType
    ) : SignupAction()

    data object OnPasswordVisibilityChange : SignupAction()

    data object OnSignup : SignupAction()

    object DismissFeedback : SignupAction()

}