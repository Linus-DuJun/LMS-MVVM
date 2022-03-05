package org.linus.lms.auth.data.network.api

import com.haroldadmin.cnradapter.NetworkResponse
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import org.linus.base.domain.exception.AppException
import org.linus.base.util.network.NetworkManager
import org.linus.base.util.network.requestApiWithNetworkChecking
import org.linus.lms.hilt.qualifiers.LmsRetrofit
import retrofit2.Retrofit
import java.net.HttpURLConnection
import javax.inject.Inject

class AuthService @Inject constructor(
    private val networkManager: NetworkManager,
    @LmsRetrofit retrofit: Retrofit
) {
    companion object {
        val APPLICATION_JSON = "application/json; charset=utf-8".toMediaType()
    }
    private val authApi: AuthApi by lazy { retrofit.create(AuthApi::class.java) }

    suspend fun getAuthType(email: String): Result<List<String>> {
       return requestApiWithNetworkChecking(networkManager) {
            when(val response = authApi.getAuthTypes(email = email)) {
               is NetworkResponse.Success -> Result.success(response.body)
               is NetworkResponse.Error -> {
                   if(response is NetworkResponse.ServerError && response.code == HttpURLConnection.HTTP_NOT_FOUND) {
                       Result.failure(AppException.EmailNotRegisteredException(email))
                   } else {
                       Result.failure(AppException.ServerException(message = response.body?.message))
                   }
               }
           }
        }
    }

    suspend fun signup(email: String, fcmToken: String) {
        JSONObject().apply {
            put("email", email)
            put("fcmToken", "")
        }.let {
            it.toString().toRequestBody(APPLICATION_JSON)
        }.run {
            authApi.signup(this)
        }
    }
}