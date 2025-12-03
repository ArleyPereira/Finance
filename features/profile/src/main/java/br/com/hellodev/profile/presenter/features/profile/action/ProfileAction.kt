package br.com.hellodev.profile.presenter.features.profile.action

import android.net.Uri
import br.com.hellodev.core.enums.input.InputType

sealed class ProfileAction {
    data object Update : ProfileAction()
    data class OnTextFieldChanged(val value: String, val type: InputType) : ProfileAction()
    data class OnChangeImage(val uri: Uri) : ProfileAction()
}