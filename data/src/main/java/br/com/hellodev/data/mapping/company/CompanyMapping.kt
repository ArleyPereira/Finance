package br.com.hellodev.data.mapping.company

import br.com.hellodev.data.model.response.company.CompanyResponse
import br.com.hellodev.domain.model.company.CompanyDomain

fun CompanyResponse.toDomain(): CompanyDomain {
    return CompanyDomain(
        id = id,
        name = name,
        image = image
    )
}