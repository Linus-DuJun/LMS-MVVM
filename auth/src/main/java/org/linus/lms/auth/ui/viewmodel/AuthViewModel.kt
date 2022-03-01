package org.linus.lms.auth.ui.viewmodel

import org.linus.base.platform.ui.SingleLiveEvent
import org.linus.base.platform.ui.viewmodel.BaseViewModel

class AuthViewModel: BaseViewModel() {

    val onContinueEvent = SingleLiveEvent<Unit>()
    val onUserLoggedInEvent = SingleLiveEvent<Unit>()

    fun onContinueClick() {
        onContinueEvent.call()
    }

    fun onLoginClick() {
        onUserLoggedInEvent.call()
    }
}