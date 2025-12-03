package br.com.hellodev.domain.repository.remote.user

import android.net.Uri
import br.com.hellodev.domain.model.user.User

interface UserRepository {

    suspend fun save(user: User)

    suspend fun getUser(): User

    suspend fun saveImage(uri: Uri): String

}