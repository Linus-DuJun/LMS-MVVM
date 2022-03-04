package org.linus.lms.auth.data.repository

import org.linus.lms.auth.data.network.api.AuthService
import org.linus.lms.auth.domain.repository.AuthRepository
import retrofit2.Retrofit
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService
) : AuthRepository {
    override suspend fun getAuthType(email: String): List<String> {
        val res = authService.getAuthType(email)
        return res.getOrThrow()
    }
}