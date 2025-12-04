package br.com.hellodev.data.mapping.user

import br.com.hellodev.data.model.response.user.UserResponse
import br.com.hellodev.domain.model.user.User

fun UserResponse.toDomain(): User {
    return User(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email,
        avatar = avatar
    )
}