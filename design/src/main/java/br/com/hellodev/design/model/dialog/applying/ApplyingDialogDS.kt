package br.com.hellodev.design.model.dialog.applying

import br.com.hellodev.core.enums.dialog.applying.ApplyingDialogType

data class ApplyingDialogDS(
    val title: String,
    val description: String,
    val firsButtonText: String,
    val secondButtonText: String,
    val type: ApplyingDialogType
)
