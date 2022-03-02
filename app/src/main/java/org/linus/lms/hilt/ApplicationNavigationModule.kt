package org.linus.lms.hilt

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.linus.lms.auth.ui.navigation.AuthNavigator
import org.linus.lms.navigation.AuthNavigatorImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class ApplicationNavigationModule {

    @Binds
    abstract fun bindAuthNavigator(
        authNavigatorImpl: AuthNavigatorImpl
    ): AuthNavigator
}