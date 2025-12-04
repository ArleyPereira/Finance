package br.com.hellodev.profile.presenter.features.profile.state

import android.net.Uri
import br.com.hellodev.domain.model.feedback.Feedback
import br.com.hellodev.domain.model.user.User

data class ProfileState(
    val isLoading: Boolean = true,
    val isUploading: Boolean = false,
    val user: User? = null,
    val uriPdf: Uri? = null,
    val feedback: Feedback? = null
)
