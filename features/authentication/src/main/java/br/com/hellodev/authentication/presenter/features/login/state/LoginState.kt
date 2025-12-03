package br.com.hellodev.authentication.presenter.features.login.state

import br.com.hellodev.core.enums.input.login.LoginInputType
import br.com.hellodev.domain.model.feedback.Feedback

data class LoginState(
    val isLoading: Boolean = false,
    val email: String = "arley@gmail.com",
    val password: String = "teste123",
    val passwordVisibility: Boolean = false,
    val feedback: Feedback? = null,
    val inputError: LoginInputType? = null
)
