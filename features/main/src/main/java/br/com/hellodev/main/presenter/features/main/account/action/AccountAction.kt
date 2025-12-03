package br.com.hellodev.main.presenter.features.main.account.action

import android.net.Uri

sealed class AccountAction {
    data object OnLogout : AccountAction()
    data object OnDeleteCurriculum : AccountAction()
    data class OnSelectedCurriculum(val uri: Uri) : AccountAction()
}