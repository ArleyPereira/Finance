package br.com.hellodev.data.model.response.banner

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BannerResponse(
    @SerialName("id")
    val id: Int? = null,

    @SerialName("image")
    val image: String? = null,

    @SerialName("url")
    val url: String? = null
)
