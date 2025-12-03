package br.com.hellodev.authentication.presenter.features.signup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hellodev.authentication.presenter.features.signup.action.SignupAction
import br.com.hellodev.authentication.presenter.features.signup.event.SignupEvent
import br.com.hellodev.authentication.presenter.features.signup.state.SignupState
import br.com.hellodev.core.enums.feedback.FeedbackType
import br.com.hellodev.core.enums.input.signup.SignupInputType
import br.com.hellodev.core.enums.input.signup.SignupInputType.EMAIL
import br.com.hellodev.core.enums.input.signup.SignupInputType.FIRST_NAME
import br.com.hellodev.core.enums.input.signup.SignupInputType.LAST_NAME
import br.com.hellodev.core.enums.input.signup.SignupInputType.PASSWORD
import br.com.hellodev.core.firebase.FirebaseHelper
import br.com.hellodev.core.functions.capitalizeEachWord
import br.com.hellodev.core.functions.isValidEmail
import br.com.hellodev.core.functions.isValidName
import br.com.hellodev.domain.model.feedback.Feedback
import br.com.hellodev.domain.model.user.User
import br.com.hellodev.domain.usecase.remote.authentication.RegisterUseCase
import br.com.hellodev.domain.usecase.remote.user.SaveUserUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignupViewModel(
    private val registerUseCase: RegisterUseCase,
    private val saveUserUseCase: SaveUserUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SignupState())
    val state = _state.asStateFlow()

    private var _event: Channel<SignupEvent> = Channel()
    var event = _event.receiveAsFlow()

    fun submitAction(action: SignupAction) {
        when (action) {
            is SignupAction.OnValueChange -> {
                onValueChange(action.value, action.type)
            }

            is SignupAction.OnPasswordVisibilityChange -> {
                onPasswordVisibilityChange()
            }

            is SignupAction.OnSignup -> {
                onSignup()
            }

            is SignupAction.DismissFeedback -> {
                dismissFeedback()
            }
        }
    }

    private fun onSignup() {
        viewModelScope.launch {
            try {
                if (!isValidData()) {
                    inputFeedbackError()
                    return@launch
                }

                _state.update { it.copy(isLoading = true) }

                registerUseCase(
                    email = _state.value.email,
                    password = _state.value.password
                )

                saveUserUseCase(
                    user = User(
                        photo = "",
                        firstName = _state.value.firstName,
                        lastName = _state.value.lastName,
                        email = _state.value.email,
                        phone = "",
                    )
                )

                _event.send(SignupEvent.Navigation.Main)
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

    private fun onValueChange(value: String, type: SignupInputType) {
        when (type) {
            FIRST_NAME -> {
                _state.update { it.copy(firstName = capitalizeEachWord(value).trim()) }
            }

            LAST_NAME -> {
                _state.update { it.copy(lastName = capitalizeEachWord(value).trim()) }
            }

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
        val firstName = isValidName(_state.value.firstName)
        val surName = isValidName(_state.value.lastName)
        val email = isValidEmail(_state.value.email)
        val password = _state.value.password.isNotEmpty()

        return firstName && surName && email && password
    }

    private fun inputFeedbackError() {
        val inputError = when {
            !isValidName(_state.value.firstName) -> FIRST_NAME
            !isValidName(_state.value.lastName) -> LAST_NAME
            !isValidEmail(_state.value.email) -> EMAIL
            _state.value.password.isEmpty() -> PASSWORD
            else -> null
        }

        _state.update { it.copy(inputError = inputError) }
    }

    private fun onPasswordVisibilityChange() {
        _state.update { currentState ->
            currentState.copy(passwordVisibility = !currentState.passwordVisibility)
        }
    }

    private fun dismissFeedback() {
        _state.update { it.copy(feedback = null) }
    }

}