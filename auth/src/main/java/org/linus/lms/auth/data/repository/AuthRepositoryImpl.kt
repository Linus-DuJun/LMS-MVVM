package org.linus.lms.auth.data.repository

import org.linus.lms.auth.data.network.api.AuthService
import org.linus.lms.auth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService
) : AuthRepository {
    override suspend fun getAuthType(email: String): List<String> =
        authService.getAuthType(email).getOrThrow()

    override suspend fun signup(email: String, fcmToken: String) =
        authService.signup(email, fcmToken)


}