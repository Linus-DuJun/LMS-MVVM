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
            val params = GetVerifyCodeUseCase.GetVerifyCodeParam(email = "test888@mailinator.com")
            getVerifyCodeUseCase(params = params) {
                it.onSuccess { types ->
                    print("type is ${types[0]}")
                }
                it.onFailure {
                    print("throwable is $it")
                }
            }

        }
    }

    fun onLoginClick() {
        onUserLoggedInEvent.call()
    }
}