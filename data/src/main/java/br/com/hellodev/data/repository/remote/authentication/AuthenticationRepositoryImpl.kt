package br.com.hellodev.data.repository.remote.authentication

import br.com.hellodev.core.firebase.FirebaseHelper
import br.com.hellodev.domain.repository.remote.authentication.AuthenticationRepository
import kotlin.coroutines.suspendCoroutine

class AuthenticationRepositoryImpl : AuthenticationRepository {

    override suspend fun register(email: String, password: String) {
        return suspendCoroutine { continuation ->
            FirebaseHelper.getAuth().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        continuation.resumeWith(Result.success(Unit))
                    } else {
                        task.exception?.let { exception ->
                            continuation.resumeWith(Result.failure(exception))
                        }
                    }
                }
        }
    }

    override suspend fun login(email: String, password: String) {
        return suspendCoroutine { continuation ->
            FirebaseHelper.getAuth().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        continuation.resumeWith(Result.success(Unit))
                    } else {
                        task.exception?.let { exception ->
                            continuation.resumeWith(Result.failure(exception))
                        }
                    }
                }
        }
    }

}