package br.com.hellodev.data.model.response.home

import br.com.hellodev.data.model.response.banner.BannerResponse
import br.com.hellodev.data.model.response.job.section.JobSectionResponse
import br.com.hellodev.data.model.response.user.UserResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeResponse(
    @SerialName("profile")
    val profile: UserResponse? = null,

    @SerialName("banners")
    val banners: List<BannerResponse>? = null,

    @SerialName("recommendation")
    val recommendation: JobSectionResponse? = null,

    @SerialName("recent")
    val recent: JobSectionResponse? = null
)
