package br.com.hellodev.data.mapping.banner

import br.com.hellodev.data.model.response.banner.BannerResponse
import br.com.hellodev.domain.model.banner.BannerDomain

fun BannerResponse.toDomain(): BannerDomain {
    return BannerDomain(
        id = id,
        image = image,
        url = url
    )
}