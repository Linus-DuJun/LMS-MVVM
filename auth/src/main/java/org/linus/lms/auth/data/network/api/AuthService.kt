package org.linus.lms.auth.data.network.api

import org.linus.lms.hilt.qualifiers.LmsRetrofit
import retrofit2.Retrofit
import javax.inject.Inject

class AuthService @Inject constructor(
    @LmsRetrofit retrofit: Retrofit
) {
}