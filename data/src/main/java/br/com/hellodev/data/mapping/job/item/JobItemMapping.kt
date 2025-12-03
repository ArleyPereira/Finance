package br.com.hellodev.data.mapping.job.item

import br.com.hellodev.data.mapping.company.toDomain
import br.com.hellodev.data.mapping.tag.toDomain
import br.com.hellodev.data.model.response.job.item.JobItemResponse
import br.com.hellodev.domain.model.job.item.JobItemDomain

fun JobItemResponse.toDomain(): JobItemDomain {
    return JobItemDomain(
        id = id,
        title = title,
        address = address,
        salary = salary,
        company = company?.toDomain(),
        tags = tags?.map { it.toDomain() }
    )
}