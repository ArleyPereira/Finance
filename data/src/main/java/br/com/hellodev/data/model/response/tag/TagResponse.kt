package br.com.hellodev.data.model.response.tag

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TagResponse(
    @SerialName("id")
    val id: Int? = null,

    @SerialName("name")
    val name: String? = null
)
