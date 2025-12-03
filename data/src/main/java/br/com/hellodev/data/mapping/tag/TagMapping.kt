package br.com.hellodev.data.mapping.tag

import br.com.hellodev.data.model.response.tag.TagResponse
import br.com.hellodev.domain.model.tag.TagDomain

fun TagResponse.toDomain(): TagDomain {
    return TagDomain(
        id = id,
        name = name
    )
}