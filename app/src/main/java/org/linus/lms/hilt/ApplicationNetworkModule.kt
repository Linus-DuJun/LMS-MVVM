package org.linus.lms.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ApplicationNetworkModule {

//    @Provides
//    fun providesOkHttpClient() {
//
//    }
//
//    @Provides
//    fun providesRetrofit() {
//
//    }
}