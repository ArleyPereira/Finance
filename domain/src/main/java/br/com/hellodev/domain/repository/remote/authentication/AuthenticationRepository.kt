package br.com.hellodev.domain.repository.remote.authentication

interface AuthenticationRepository {

    suspend fun register(email: String, password: String)

    suspend fun login(email: String, password: String)

}