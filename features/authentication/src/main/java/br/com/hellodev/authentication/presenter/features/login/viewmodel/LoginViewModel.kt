package br.com.hellodev.authentication.presenter.features.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hellodev.authentication.presenter.features.login.action.LoginAction
import br.com.hellodev.authentication.presenter.features.login.event.LoginEvent
import br.com.hellodev.authentication.presenter.features.login.state.LoginState
import br.com.hellodev.core.enums.feedback.FeedbackType
import br.com.hellodev.core.enums.input.login.LoginInputType
import br.com.hellodev.core.enums.input.login.LoginInputType.EMAIL
import br.com.hellodev.core.enums.input.login.LoginInputType.PASSWORD
import br.com.hellodev.core.firebase.FirebaseHelper
import br.com.hellodev.core.functions.isValidEmail
import br.com.hellodev.domain.model.feedback.Feedback
import br.com.hellodev.domain.usecase.remote.authentication.LoginUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private var _event: Channel<LoginEvent> = Channel()
    var event = _event.receiveAsFlow()

    init {
        verifyAuthentication()
    }

    fun submitAction(action: LoginAction) {
        when (action) {
            is LoginAction.OnValueChange -> {
                onValueChange(action.value, action.type)
            }

            is LoginAction.OnSignIn -> {
                onSignIn()
            }

            is LoginAction.DismissFeedback -> {
                dismissFeedback()
            }
        }
    }

    private fun onSignIn() {
        viewModelScope.launch {
            try {
                if (!isValidData()) {
                    inputFeedbackError()
                    return@launch
                }

                _state.update { it.copy(isLoading = true) }

                loginUseCase(
                    email = _state.value.email,
                    password = _state.value.password
                )

                _event.send(LoginEvent.Navigation.Main)
            } catch (exception: Exception) {
                exception.printStackTrace()

                _state.update {
                    it.copy(
                        feedback = Feedback(
                            show = true,
                            type = FeedbackType.ERROR,
                            title = FirebaseHelper.validError(exception.message)
                        )
                    )
                }
            }
        }
    }

    private fun verifyAuthentication() {
        viewModelScope.launch {
            if (FirebaseHelper.isAuthenticated()) {
                _event.send(LoginEvent.Navigation.Main)
            }
        }
    }

    private fun onValueChange(value: String, type: LoginInputType) {
        when (type) {
            EMAIL -> {
                _state.update { it.copy(email = value.trim()) }
            }

            PASSWORD -> {
                _state.update { it.copy(password = value) }
            }
        }

        _state.update { it.copy(inputError = null) }
    }

    private fun isValidData(): Boolean {
        val email = isValidEmail(_state.value.email)
        val password = _state.value.password.isNotEmpty()

        return email && password
    }

    private fun inputFeedbackError() {
        val inputError = when {
            !isValidEmail(_state.value.email) -> EMAIL
            _state.value.password.isEmpty() -> PASSWORD
            else -> null
        }

        _state.update { it.copy(inputError = inputError) }
    }

    private fun dismissFeedback() {
        _state.update { it.copy(feedback = null) }
    }

}