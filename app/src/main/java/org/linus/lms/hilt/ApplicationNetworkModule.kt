package org.linus.lms.hilt

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.linus.base.util.network.UserAgentInterceptor
import org.linus.base.util.network.UserSessionInterceptor
import org.linus.lms.hilt.qualifiers.LmsOkHttpClient
import org.linus.lms.hilt.qualifiers.LmsRetrofit
import org.linus.lms.hilt.qualifiers.TmdbOkHttpClient
import org.linus.lms.hilt.qualifiers.TmdbRetrofit
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationNetworkModule {

    companion object {
        private const val DISK_CACHE_SIZE =  50 * 1024 * 1024L
        private const val OKHTTP_TIMEOUT = 60_000L
        private const val LMS_BASE_URL = "https://letmesee.app"
        private const val TMDB_BASE_URL = "url_for_tmdb"
    }

    @Singleton
    @LmsOkHttpClient
    @Provides
   fun providesLmsOkHttpClient(
        @ApplicationContext context: Context,
        userSessionInterceptor: UserSessionInterceptor,
        userAgentInterceptor: UserAgentInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ) : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(userSessionInterceptor)
            .addInterceptor(userAgentInterceptor)
            .addNetworkInterceptor(httpLoggingInterceptor)
            .cache(providesOkHttpCache(context))
            .connectTimeout(OKHTTP_TIMEOUT, TimeUnit.MILLISECONDS)
            .readTimeout(OKHTTP_TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(OKHTTP_TIMEOUT, TimeUnit.MILLISECONDS)
            .build()
    }

    @Singleton
    @TmdbOkHttpClient
    @Provides
    fun providesTmdbOkHttpClient(
        @ApplicationContext context: Context,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ) : OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(httpLoggingInterceptor)
            .cache(providesOkHttpCache(context))
            .connectTimeout(OKHTTP_TIMEOUT, TimeUnit.MILLISECONDS)
            .readTimeout(OKHTTP_TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(OKHTTP_TIMEOUT, TimeUnit.MILLISECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() : HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun providesOkHttpCache(@ApplicationContext context: Context): Cache {
        val cacheDir = File(context.cacheDir, "ok-http-cache")
        return Cache(cacheDir, DISK_CACHE_SIZE)
    }

    @Singleton
    @Provides
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Singleton
    @LmsRetrofit
    @Provides
    fun providesLmsRetrofit(
        @LmsOkHttpClient okHttpClient: OkHttpClient,
        moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(LMS_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Singleton
    @TmdbRetrofit
    @Provides
    fun providesTmdbRetrofit(@TmdbOkHttpClient okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(TMDB_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

}