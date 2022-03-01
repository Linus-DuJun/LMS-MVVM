package org.linus.lms.auth.ui.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import org.linus.base.platform.ui.SingleLiveEvent
import org.linus.base.platform.ui.viewmodel.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : BaseViewModel() {

    val onContinueEvent = SingleLiveEvent<Unit>()
    val onUserLoggedInEvent = SingleLiveEvent<Unit>()

    fun onContinueClick() {
        onContinueEvent.call()
    }

    fun onLoginClick() {
        onUserLoggedInEvent.call()
    }
}