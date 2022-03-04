package org.linus.lms.auth.data.network.api

import com.haroldadmin.cnradapter.NetworkResponse
import org.linus.base.data.BaseNetworkErrorEntry
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthApi {
    @GET("/api/auth/{email}")
    suspend fun getAuthTypes(
        @Path("email") email: String
    ): NetworkResponse<List<String>, BaseNetworkErrorEntry>
}