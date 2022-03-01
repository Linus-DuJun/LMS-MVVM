package org.linus.base.platform.manager.managerImpl

import org.linus.base.platform.manager.ErrorHandlerManager
import org.linus.base.platform.ui.toast.Toaster

class ErrorHandlerManagerImpl(
    private val toaster: Toaster
) : ErrorHandlerManager {
    override fun handleError(throwable: Throwable) {
        //TODO handler error
        toaster.showToast(throwable.message.toString())
    }
}