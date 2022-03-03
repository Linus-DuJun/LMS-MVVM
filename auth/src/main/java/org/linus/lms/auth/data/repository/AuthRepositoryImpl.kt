package org.linus.lms.auth.data.repository

import org.linus.lms.auth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(

) : AuthRepository {
    override suspend fun getAuthType(email: String): List<String> {
        return listOf("email")
    }
}