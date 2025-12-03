package br.com.hellodev.domain.usecase.remote.user

import br.com.hellodev.domain.model.user.User
import br.com.hellodev.domain.repository.remote.user.UserRepository

class SaveUserUseCase(
    private val repository: UserRepository
) {

    suspend operator fun invoke(user: User) {
        repository.save(user)
    }

}