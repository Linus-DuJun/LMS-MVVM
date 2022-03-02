package org.linus.base.util.network

import android.content.Context
import androidx.datastore.preferences.core.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import org.linus.base.util.extension.KEY_SESSION_ID
import org.linus.base.util.extension.dataStore
import java.io.IOException
import javax.inject.Inject
import kotlin.jvm.Throws

class UserSessionInterceptor @Inject constructor(
    @ApplicationContext private val context: Context
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val sessionId = runBlocking {
            context.dataStore.data.map {
                it[KEY_SESSION_ID]
            }.first()
        }
        val original = chain.request()
        val requestWithSession = original.newBuilder().apply {
            addHeader("Cookie", sessionId ?: "")
        }.build()
        val response = chain.proceed(requestWithSession)
        if (sessionId.isNullOrEmpty()) {
            val cookies = response.headers.values("Set-Cookie")
            cookies.find {
                it.contains("userSessionId")
            }?.split(";")
                ?.first()
                ?.also { sessionId ->
                    runBlocking {
                        context.dataStore.edit {
                            it[KEY_SESSION_ID] = sessionId
                        }
                    }
                }
        }
        return response
    }
}