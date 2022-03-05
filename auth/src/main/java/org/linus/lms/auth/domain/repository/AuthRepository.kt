package org.linus.lms.auth.domain.repository

interface AuthRepository {
    suspend fun getAuthType(email: String): List<String>
    suspend fun signup(email: String, fcmToken: String)
}