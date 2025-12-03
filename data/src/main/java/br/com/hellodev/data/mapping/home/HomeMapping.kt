package br.com.hellodev.data.mapping.home

import br.com.hellodev.data.mapping.banner.toDomain
import br.com.hellodev.data.mapping.job.section.toDomain
import br.com.hellodev.data.mapping.user.toDomain
import br.com.hellodev.data.model.response.home.HomeResponse
import br.com.hellodev.domain.model.home.HomeDomain

fun HomeResponse.toDomain(): HomeDomain {
    return HomeDomain(
        profile = profile?.toDomain(),
        banners = banners?.map { it.toDomain() },
        recommendation = recommendation?.toDomain(),
        recent = recent?.toDomain()
    )
}