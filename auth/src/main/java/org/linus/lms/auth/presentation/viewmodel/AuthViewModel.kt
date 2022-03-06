package org.linus.lms.auth.presentation.viewmodel

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import org.linus.base.domain.exception.AppException
import org.linus.base.presentation.ui.SingleLiveEvent
import org.linus.base.presentation.ui.viewmodel.BaseViewModel
import org.linus.lms.auth.domain.usecase.GetVerifyCodeUseCase
import org.linus.lms.auth.domain.usecase.SignupUseCase
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val getVerifyCodeUseCase: GetVerifyCodeUseCase,
    private val signupUseCase: SignupUseCase
) : BaseViewModel() {

    val email = MutableLiveData<String>()
    val isEmailValid = Transformations.map(email) {
        Patterns.EMAIL_ADDRESS.matcher(it).matches()
    }
    val openVerifyCodeScreenEvent = SingleLiveEvent<Unit>()
    val userLoggedInEvent = SingleLiveEvent<Unit>()
    val confirmAccountEvent = SingleLiveEvent<String>()
    val accountVerifyFailedEvent = SingleLiveEvent<String>()

    fun onContinueClick() {
        // ignore email check, assume email is valid, for demo, this is enough
        viewModelScope.safeLaunch {
            val params = GetVerifyCodeUseCase.GetVerifyCodeParam(email = email.value!!)
            getVerifyCodeUseCase(params = params) {
                openVerifyCodeScreenEvent.call()
            }
        }
    }

    fun signup() {
        viewModelScope.safeLaunch {
            val params = SignupUseCase.SignupParams(email = email.value!!, "")
            signupUseCase(params = params) {
                confirmAccountEvent.postValue(email.value!!)
            }
        }
    }

    override fun sendError(error: Throwable) {
        when (error) {
            is AppException.EmailNotRegisteredException -> signup()
            is AppException.AccountVerifyFailedException -> {
                accountVerifyFailedEvent.postValue(error.message)
            }
            else -> super.sendError(error)
        }
    }

    fun onLoginClick() {
        userLoggedInEvent.call()
    }

}