package org.linus.lms.hilt

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.linus.base.presentation.ui.toast.SimpleToaster
import org.linus.base.presentation.ui.toast.Toaster
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ApplicationModule {

    @Singleton
    @Binds
    abstract fun bindToaster(
        simpleToaster: SimpleToaster
    ): Toaster
}