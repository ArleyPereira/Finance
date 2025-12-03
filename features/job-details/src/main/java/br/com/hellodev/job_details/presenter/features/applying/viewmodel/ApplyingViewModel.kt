package br.com.hellodev.job_details.presenter.features.applying.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hellodev.core.enums.dialog.applying.ApplyingDialogType.APPLYING_SUCCESS
import br.com.hellodev.core.enums.input.InputType
import br.com.hellodev.core.enums.input.InputType.EMAIL
import br.com.hellodev.core.enums.input.InputType.FULL_NAME
import br.com.hellodev.core.enums.input.InputType.UPLOAD
import br.com.hellodev.core.functions.capitalizeEachWord
import br.com.hellodev.core.functions.isValidEmail
import br.com.hellodev.core.functions.isValidFullName
import br.com.hellodev.design.model.dialog.applying.ApplyingDialogDS
import br.com.hellodev.job_details.presenter.features.applying.action.ApplyingAction
import br.com.hellodev.job_details.presenter.features.applying.state.ApplyingState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ApplyingViewModel(

) : ViewModel() {

    private var _state = MutableStateFlow(ApplyingState())
    var state: StateFlow<ApplyingState> = _state

    init {

    }

    fun dispatchAction(action: ApplyingAction) {
        when (action) {
            is ApplyingAction.Send -> {
                send()
            }

            is ApplyingAction.OnSelectedCurriculum -> {
                onSelectedCurriculum(uri = action.uri)
            }

            is ApplyingAction.OnDeleteCurriculum -> {
                onDeleteCurriculum()
            }

            is ApplyingAction.OnTextFieldChanged -> {
                onTextFieldChanged(
                    value = action.value,
                    type = action.type
                )
            }

            is ApplyingAction.ClearDialog -> {
                _state.update { currentState ->
                    currentState.copy(dialogDS = null)
                }
            }
        }
    }

    private fun send() {
        viewModelScope.launch {
            if (!isApplyValid()) {
                inputFeedbackError()
                return@launch
            }

            _state.update { currentState ->
                currentState.copy(isLoading = true)
            }

            delay(3000)

            _state.update { currentState ->
                currentState.copy(
                    dialogDS = ApplyingDialogDS(
                        title = "Parabéns!",
                        description = "A sua candidatura foi submetida com sucesso. Você pode acompanhar o andamento da sua inscrição através do menu de inscrições.",
                        firsButtonText = "Minhas inscrições",
                        secondButtonText = "Cancelar",
                        type = APPLYING_SUCCESS
                    ),
                    isLoading = false
                )
            }

            // Envia os dados do perfil para algum lugar
        }
    }

    private fun onTextFieldChanged(value: String, type: InputType?) {
        clearError()

        _state.update { currentState ->
            when (type) {
                FULL_NAME -> currentState.copy(fullName = capitalizeEachWord(value))
                EMAIL -> currentState.copy(email = value.lowercase())
                else -> currentState.copy(coverLetter = value)
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
                    isUploading = false,
                    inputError = null
                )
            }
        }
    }

    private fun onDeleteCurriculum() {
        _state.update { currentState ->
            currentState.copy(uriPdf = null)
        }
    }

    private fun inputFeedbackError() {
        val inputError = when {
            !isValidFullName(_state.value.fullName) -> FULL_NAME
            !isValidEmail(_state.value.email) -> EMAIL
            _state.value.uriPdf == null -> UPLOAD
            else -> null
        }

        _state.update { currentState ->
            currentState.copy(inputError = inputError)
        }
    }

    private fun isApplyValid(): Boolean {
        val fullName = isValidFullName(_state.value.fullName)
        val email = isValidEmail(_state.value.email)
        val curriculum = _state.value.uriPdf != null

        return fullName && email && curriculum
    }

    private fun clearError() {
        _state.update { currentState ->
            currentState.copy(inputError = null)
        }
    }

}