package org.linus.base.util.network

import android.os.Build
import okhttp3.Interceptor
import okhttp3.Response
import org.linus.base.util.DeviceUuidFactory
import java.io.IOException
import javax.inject.Inject

class UserAgentInterceptor @Inject constructor(
    private val deviceUuidFactory: DeviceUuidFactory
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.request()
            .newBuilder()
            .addHeader("User-Agent", getUserAgent())
            .build().let(chain::proceed)
    }

    private fun getUserAgent(): String {
        // latest version on google play
        return "Letmesee 2.0.6; Android ${Build.VERSION.RELEASE}; ${Build.MANUFACTURER} (${Build.MODEL}); SN ${deviceUuidFactory.getDeviceUuid()}"
    }
}