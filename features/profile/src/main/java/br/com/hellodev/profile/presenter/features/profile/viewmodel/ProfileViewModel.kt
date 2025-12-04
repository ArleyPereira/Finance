package br.com.hellodev.profile.presenter.features.profile.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hellodev.core.enums.feedback.FeedbackType
import br.com.hellodev.core.firebase.FirebaseHelper
import br.com.hellodev.domain.model.feedback.Feedback
import br.com.hellodev.domain.model.user.User
import br.com.hellodev.domain.usecase.remote.user.GetUserUseCase
import br.com.hellodev.profile.presenter.features.profile.action.ProfileAction
import br.com.hellodev.profile.presenter.features.profile.state.ProfileState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getUserUseCase: GetUserUseCase,
) : ViewModel() {

    private var _state = MutableStateFlow(ProfileState())
    var state: StateFlow<ProfileState> = _state

    init {
        getUser()
    }

    fun dispatchAction(action: ProfileAction) {
        when (action) {
            is ProfileAction.OnLogout -> {
                onLogout()
            }

            is ProfileAction.OnSelectedCurriculum -> {
                onSelectedCurriculum(uri = action.uri)
            }

            is ProfileAction.OnDeleteCurriculum -> {
                onDeleteCurriculum()
            }
        }
    }

    private fun getUser() {
        viewModelScope.launch {
            try {
                _state.update {
                    it.copy(
                        isLoading = true,
                        user = User.userDefault
                    )
                }

                _state.update {
                    it.copy(
                        user = getUserUseCase(),
                        isLoading = false
                    )
                }
            } catch (exception: Exception) {
                exception.printStackTrace()

                _state.update {
                    it.copy(
                        user = null,
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

    private fun onSelectedCurriculum(uri: Uri) {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(isUploading = true)
            }

            delay(2000)

            _state.update { currentState ->
                currentState.copy(
                    uriPdf = uri,
                    isUploading = false
                )
            }
        }
    }

    private fun onDeleteCurriculum() {
        _state.update { currentState ->
            currentState.copy(uriPdf = null)
        }
    }

    private fun onLogout() {

    }

}