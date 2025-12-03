package br.com.hellodev.authentication.presenter.features.signup.state

import br.com.hellodev.core.enums.input.signup.SignupInputType
import br.com.hellodev.domain.model.feedback.Feedback

data class SignupState(
    val isLoading: Boolean = false,
    val firstName: String = "Arley",
    val lastName: String = "Santana",
    val email: String = "arley@gmail.com",
    val password: String = "teste123",
    val passwordVisibility: Boolean = false,
    val feedback: Feedback? = null,
    val inputError: SignupInputType? = null
)
