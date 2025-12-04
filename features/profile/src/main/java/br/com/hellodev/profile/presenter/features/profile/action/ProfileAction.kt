package br.com.hellodev.profile.presenter.features.profile.action

import android.net.Uri

sealed class ProfileAction {
    data object OnLogout : ProfileAction()
    data object OnDeleteCurriculum : ProfileAction()
    data class OnSelectedCurriculum(val uri: Uri) : ProfileAction()
}