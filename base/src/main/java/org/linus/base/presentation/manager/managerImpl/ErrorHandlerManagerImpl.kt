package org.linus.base.presentation.manager.managerImpl

import org.linus.base.presentation.manager.ErrorHandlerManager
import org.linus.base.presentation.ui.toast.Toaster

class ErrorHandlerManagerImpl(
    private val toaster: Toaster
) : ErrorHandlerManager {
    override fun handleError(throwable: Throwable) {
        //TODO handler error
        toaster.showToast(throwable.message.toString())
    }
}