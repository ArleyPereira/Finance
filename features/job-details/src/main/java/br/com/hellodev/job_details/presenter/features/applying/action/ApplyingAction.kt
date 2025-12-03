package br.com.hellodev.job_details.presenter.features.applying.action

import android.net.Uri
import br.com.hellodev.core.enums.input.InputType

sealed class ApplyingAction {
    data object Send : ApplyingAction()
    data class OnSelectedCurriculum(val uri: Uri) : ApplyingAction()
    data class OnTextFieldChanged(val value: String, val type: InputType?) : ApplyingAction()
    data object OnDeleteCurriculum : ApplyingAction()
    data object ClearDialog : ApplyingAction()
}