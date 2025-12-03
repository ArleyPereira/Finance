package br.com.hellodev.data.model.response.job.item

import br.com.hellodev.data.model.response.company.CompanyResponse
import br.com.hellodev.data.model.response.tag.TagResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JobItemResponse(
    @SerialName("id")
    val id: Int? = null,

    @SerialName("title")
    val title: String? = null,

    @SerialName("address")
    val address: String? = null,

    @SerialName("salary")
    val salary: String? = null,

    @SerialName("company")
    val company: CompanyResponse? = null,

    @SerialName("tags")
    val tags: List<TagResponse>? = null
)