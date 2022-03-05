package org.linus.lms.auth.data.network.api

import com.haroldadmin.cnradapter.NetworkResponse
import okhttp3.RequestBody
import org.linus.base.data.BaseNetworkErrorEntry
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthApi {
    @GET("/api/auth/{email}")
    suspend fun getAuthTypes(
        @Path("email") email: String
    ): NetworkResponse<List<String>, BaseNetworkErrorEntry>

    @POST("/api/users/")
    suspend fun signup(@Body body: RequestBody)
}