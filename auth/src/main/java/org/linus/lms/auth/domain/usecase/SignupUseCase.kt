package org.linus.lms.auth.domain.usecase

import dagger.hilt.android.scopes.ViewModelScoped
import org.linus.base.domain.usecase.BaseUseCase
import org.linus.lms.auth.domain.repository.AuthRepository
import javax.inject.Inject

@ViewModelScoped
class SignupUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : BaseUseCase<Unit, SignupUseCase.SignupParams>(){

    override suspend fun run(params: SignupParams)
        = authRepository.signup(params.email, params.fcmToken)

    data class SignupParams(
        val email: String,
        val fcmToken: String
    )
}