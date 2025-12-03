package br.com.hellodev.profile.presenter.features.profile.state

import android.net.Uri
import br.com.hellodev.core.enums.input.InputType
import br.com.hellodev.domain.model.feedback.Feedback

data class ProfileState(
    val isLoading: Boolean = true,
    val isLoadingImage: Boolean = false,
    val photo: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val phone: String = "",
    val urlImageProfile: String? = null,
    val inputError: InputType? = null,
    val selectedImageUri: Uri = Uri.EMPTY,
    val feedback: Feedback? = null
)
