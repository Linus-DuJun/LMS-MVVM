package org.linus.lms.auth.ui.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import org.linus.base.platform.ui.SingleLiveEvent
import org.linus.base.platform.ui.viewmodel.BaseViewModel
import org.linus.lms.auth.domain.usecase.GetVerifyCodeUseCase
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val getVerifyCodeUseCase: GetVerifyCodeUseCase
) : BaseViewModel() {

    val onContinueEvent = SingleLiveEvent<Unit>()
    val onUserLoggedInEvent = SingleLiveEvent<Unit>()

    fun onContinueClick() {
        viewModelScope.safeLaunch {
            val params = GetVerifyCodeUseCase.GetVerifyCodeParam(email = "linus.du@axel.org")
            getVerifyCodeUseCase(params = params) {
                it.onFailure {
                   print("failure is got")
                }
                it.onSuccess {
                    onContinueEvent.call()
                }
            }
        }
    }

    fun onLoginClick() {
        onUserLoggedInEvent.call()
    }
}