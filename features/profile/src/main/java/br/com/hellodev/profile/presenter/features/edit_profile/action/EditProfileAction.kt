package br.com.hellodev.profile.presenter.features.edit_profile.action

import android.net.Uri
import br.com.hellodev.core.enums.input.InputType

sealed class EditProfileAction {

    object Update : EditProfileAction()
    object DismissFeedback : EditProfileAction()

    data class OnTextFieldChanged(val value: String, val type: InputType) : EditProfileAction()
    data class OnChangeImage(val uri: Uri) : EditProfileAction()

}