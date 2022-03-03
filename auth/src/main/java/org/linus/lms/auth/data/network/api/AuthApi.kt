package org.linus.lms.auth.data.network.api

import retrofit2.http.GET
import retrofit2.http.Path

interface AuthApi {
    @GET("/api/auth/{email}")
    suspend fun getAuthTypes(
        @Path("email") email: String
    ): List<String>
}