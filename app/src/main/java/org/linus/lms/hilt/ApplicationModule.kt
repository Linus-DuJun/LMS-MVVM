package org.linus.lms.hilt

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.linus.base.platform.ui.toast.SimpleToaster
import org.linus.base.platform.ui.toast.Toaster

@Module
@InstallIn(SingletonComponent::class)
abstract class ApplicationModule {

    @Binds
    abstract fun bindToaster(
        simpleToaster: SimpleToaster
    ): Toaster
}