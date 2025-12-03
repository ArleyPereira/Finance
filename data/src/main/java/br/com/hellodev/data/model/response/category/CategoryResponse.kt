package br.com.hellodev.data.model.response.category

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryResponse(
    @SerialName("id")
    val id: Int? = null,

    @SerialName("title")
    val title: String? = null
)
