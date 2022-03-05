package org.linus.lms.auth.ui.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import org.linus.base.platform.ui.SingleLiveEvent
import org.linus.base.platform.ui.viewmodel.BaseViewModel
import org.linus.lms.auth.domain.usecase.GetVerifyCodeUseCase
import org.linus.lms.auth.domain.usecase.SignupUseCase
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val getVerifyCodeUseCase: GetVerifyCodeUseCase,
    private val signupUseCase: SignupUseCase
) : BaseViewModel() {

    val onContinueEvent = SingleLiveEvent<Unit>()
    val onUserLoggedInEvent = SingleLiveEvent<Unit>()
    val onConfirmAccountEvent = SingleLiveEvent<String>()

    fun onContinueClick() {
        viewModelScope.safeLaunch {
            val params = GetVerifyCodeUseCase.GetVerifyCodeParam(email = "dec8@mailinator.com")
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

    fun signup() {
        viewModelScope.safeLaunch {
            val params = SignupUseCase.SignupParams("test999@mailinator.com", "")
            signupUseCase(params = params) {
                it.onSuccess {
                    onConfirmAccountEvent.postValue("test999@mailinator.com")
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