package br.com.hellodev.domain.usecase.remote.user

import android.net.Uri
import br.com.hellodev.domain.repository.remote.user.UserRepository

class SaveImageUserUseCase(
    private val repository: UserRepository
) {

    suspend operator fun invoke(uri: Uri): String {
        return repository.saveImage(uri)
    }

}