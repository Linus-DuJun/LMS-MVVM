package org.linus.base.presentation.manager

/**
 * Handle an error that arises in application and if needed show notification to user that something wrong
 */
interface ErrorHandlerManager {
    /**
     * Handle error
     * Run on UI thread all interactions with user interface because method may be called by worker thread.
     */
    fun handleError(throwable: Throwable)
}