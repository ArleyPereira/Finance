package br.com.hellodev.domain.usecase.remote.user

import br.com.hellodev.domain.model.user.User
import br.com.hellodev.domain.repository.remote.user.UserRepository

class GetUserUseCase(
    private val repository: UserRepository
) {

    suspend operator fun invoke(): User {
        return repository.getUser()
    }

}