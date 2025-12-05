package br.com.hellodev.data.mapping.job.section

import br.com.hellodev.data.mapping.category.toDomain
import br.com.hellodev.data.mapping.job.item.toDomain
import br.com.hellodev.data.model.response.job.section.JobSectionResponse
import br.com.hellodev.domain.model.section.JobSectionDomain

fun JobSectionResponse.toDomain(): JobSectionDomain {
    return JobSectionDomain(
        id = id,
        leftTitle = leftTitle,
        rightTitle = rightTitle,
        categories = categories?.map { it.toDomain() },
        items = items?.map { it.toDomain() }
    )
}