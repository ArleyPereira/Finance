package br.com.hellodev.job_details.presenter.features.applying.state

import android.net.Uri
import br.com.hellodev.core.enums.input.InputType
import br.com.hellodev.design.model.dialog.applying.ApplyingDialogDS

data class ApplyingState(
    val isLoading: Boolean = false,
    val isUploading: Boolean = false,
    val uriPdf: Uri? = null,
    val fullName: String = "",
    val email: String = "",
    val coverLetter: String = "",
    val inputError: InputType? = null,
    val dialogDS: ApplyingDialogDS? = null
)
