package br.com.hellodev.main.presenter.features.main.account.state

import android.net.Uri
import br.com.hellodev.domain.model.user.User

data class AccountState(
    val isLoading: Boolean = true,
    val isUploading: Boolean = false,
    val user: User? = null,
    val uriPdf: Uri? = null
)
