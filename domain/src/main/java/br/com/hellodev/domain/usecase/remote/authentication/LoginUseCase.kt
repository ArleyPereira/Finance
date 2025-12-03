package br.com.hellodev.domain.usecase.remote.authentication

import br.com.hellodev.domain.repository.remote.authentication.AuthenticationRepository

class LoginUseCase(
    private val repository: AuthenticationRepository
) {

    suspend operator fun invoke(email: String, password: String) {
        repository.login(email, password)
    }

}