package br.com.hellodev.profile.presenter.features.profile.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hellodev.core.enums.feedback.FeedbackType
import br.com.hellodev.core.enums.input.InputType
import br.com.hellodev.core.enums.input.InputType.FIRST_NAME
import br.com.hellodev.core.enums.input.InputType.LAST_NAME
import br.com.hellodev.core.enums.input.InputType.PHONE
import br.com.hellodev.core.firebase.FirebaseHelper
import br.com.hellodev.core.functions.isValidName
import br.com.hellodev.domain.model.feedback.Feedback
import br.com.hellodev.domain.model.user.User
import br.com.hellodev.domain.usecase.remote.user.GetUserUseCase
import br.com.hellodev.domain.usecase.remote.user.SaveImageUserUseCase
import br.com.hellodev.domain.usecase.remote.user.SaveUserUseCase
import br.com.hellodev.profile.presenter.features.profile.action.ProfileAction
import br.com.hellodev.profile.presenter.features.profile.state.ProfileState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val saveUserUseCase: SaveUserUseCase,
    private val saveImageUserUseCase: SaveImageUserUseCase
) : ViewModel() {

    private var _state = MutableStateFlow(ProfileState())
    var state: StateFlow<ProfileState> = _state

    init {
        getUser()
    }

    fun dispatchAction(action: ProfileAction) {
        when (action) {
            is ProfileAction.Update -> {
                update()
            }

            is ProfileAction.OnTextFieldChanged -> {
                onTextFieldChanged(value = action.value, type = action.type)
            }

            is ProfileAction.OnChangeImage -> {
                onChangeImage(uri = action.uri)
            }
        }
    }

    private fun getUser() {
        viewModelScope.launch {
            try {
                _state.update { it.copy(isLoading = true) }

                val user = getUserUseCase()

                _state.update {
                    it.copy(
                        photo = user.photo.orEmpty(),
                        firstName = user.firstName.orEmpty(),
                        lastName = user.lastName.orEmpty(),
                        email = user.email.orEmpty(),
                        phone = user.phone.orEmpty(),
                        isLoading = false
                    )
                }
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

    private fun update() {
        viewModelScope.launch {
            try {
                if (!isValidProfile()) {
                    inputFeedbackError()
                    return@launch
                }

                if (_state.value.selectedImageUri != Uri.EMPTY) {
                    saveImageProfile()
                } else {
                    updateProfile()
                }
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

    private fun updateProfile() {
        viewModelScope.launch {
            try {
                if (!isValidProfile()) {
                    inputFeedbackError()
                    return@launch
                }

                _state.update { it.copy(isLoading = true) }

                val photo = _state.value.urlImageProfile ?: _state.value.photo
                val user = User(
                    photo = photo,
                    firstName = _state.value.firstName,
                    lastName = _state.value.lastName,
                    phone = _state.value.phone,
                    email = _state.value.email
                )

                saveUserUseCase(user = user)

                _state.update {
                    it.copy(
                        isLoading = false,
                        isLoadingImage = false,
                        feedback = Feedback(
                            show = true,
                            type = FeedbackType.SUCCESS,
                            title = "Perfil atualizado com sucesso!"
                        )
                    )
                }
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

    private fun saveImageProfile() {
        viewModelScope.launch {
            try {
                _state.update {
                    it.copy(
                        isLoadingImage = true,
                        isLoading = true
                    )
                }

                val url = saveImageUserUseCase(uri = _state.value.selectedImageUri)

                _state.update { it.copy(urlImageProfile = url) }

                updateProfile()
            } catch (exception: Exception) {
                exception.printStackTrace()

                _state.update {
                    it.copy(
                        isLoadingImage = false,
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

    private fun onTextFieldChanged(value: String, type: InputType) {
        when (type) {
            FIRST_NAME -> {
                _state.update { it.copy(firstName = value) }
            }

            LAST_NAME -> {
                _state.update { it.copy(lastName = value) }
            }

            PHONE -> {
                _state.update { it.copy(phone = value) }
            }

            else -> {}
        }

        clearError()
    }

    private fun onChangeImage(uri: Uri) {
        _state.update { currentState ->
            currentState.copy(selectedImageUri = uri)
        }
    }

    private fun inputFeedbackError() {
        val inputError = when {
            !isValidName(_state.value.firstName) -> FIRST_NAME
            !isValidName(_state.value.lastName) -> LAST_NAME
            else -> null
        }

        _state.update { currentState ->
            currentState.copy(inputError = inputError)
        }
    }

    private fun isValidProfile(): Boolean {
        val name = isValidName(_state.value.firstName)
        val surname = isValidName(_state.value.lastName)

        return name && surname
    }

    private fun clearError() {
        _state.update { currentState ->
            currentState.copy(inputError = null)
        }
    }

}