package br.com.hellodev.domain.model.feedback

import br.com.hellodev.core.enums.feedback.FeedbackType

data class Feedback(
    val show: Boolean = false, // Verificar se realmente precisa desse atributo
    val title: String,
    val message: String? = null,
    val type: FeedbackType
)
