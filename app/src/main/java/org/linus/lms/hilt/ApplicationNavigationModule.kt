package org.linus.lms.hilt

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.linus.lms.auth.presentation.navigation.AuthNavigator
import org.linus.lms.navigation.AuthNavigatorImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ApplicationNavigationModule {

    @Singleton
    @Binds
    abstract fun bindAuthNavigator(
        authNavigatorImpl: AuthNavigatorImpl
    ): AuthNavigator
}