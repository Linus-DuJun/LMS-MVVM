package org.linus.lms.auth.data.network.api

import com.haroldadmin.cnradapter.NetworkResponse
import org.linus.base.domain.exception.AppException
import org.linus.lms.hilt.qualifiers.LmsRetrofit
import retrofit2.Retrofit
import java.net.HttpURLConnection
import javax.inject.Inject

class AuthService @Inject constructor(
    @LmsRetrofit retrofit: Retrofit
) {
    private val authApi: AuthApi by lazy { retrofit.create(AuthApi::class.java) }

    suspend fun getAuthType(email: String): Result<List<String>> {
        return when(val response = authApi.getAuthTypes(email = email)) {
            is NetworkResponse.Success -> Result.success(response.body)
            is NetworkResponse.Error -> {
                if(response is NetworkResponse.ServerError && response.code == HttpURLConnection.HTTP_NOT_FOUND) {
                    Result.failure(AppException.EmailNotRegisteredException)
                } else {
                    Result.failure(AppException.ServerException(message = response.error?.message))
                }
            }
        }
    }
}