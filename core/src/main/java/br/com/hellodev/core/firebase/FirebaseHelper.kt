package br.com.hellodev.core.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class FirebaseHelper {
    companion object {
        fun getAuth() = FirebaseAuth.getInstance()

        fun isAuthenticated() = getAuth().currentUser != null

        fun getDatabase() = FirebaseDatabase.getInstance().reference

        fun getStorage() = FirebaseStorage.getInstance().reference

        fun getUserId() = getAuth().currentUser?.uid ?: "UNAUTHENTICATED_USER"

        fun validError(error: String?): String {
            return when {
                error?.contains("The email address is already in use by another account") == true -> {
                    "Este e-mail já está em uso."
                }

                error?.contains("Password should be at least 6 characters") == true -> {
                    "Insira uma senha mais forte."
                }

                error?.contains("The email address is badly formatted") == true -> {
                    "Insira um e-mail válido."
                }

                error?.contains("The supplied auth credential is incorrect, malformed or has expired") == true -> {
                    "A credencial de autenticação fornecida está incorreta, malformada ou expirou."
                }

                else -> {
                    "Ocorreu um erro, por favor tente novamente mais tarde."
                }
            }
        }
    }
}