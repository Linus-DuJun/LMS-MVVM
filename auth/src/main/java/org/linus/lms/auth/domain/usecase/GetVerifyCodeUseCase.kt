package org.linus.lms.auth.domain.usecase

import org.linus.base.domain.usecase.BaseUseCase
import org.linus.lms.auth.domain.repository.AuthRepository
import javax.inject.Inject

class GetVerifyCodeUseCase @Inject constructor(
    private val authRepository: AuthRepository
): BaseUseCase<List<String>, GetVerifyCodeUseCase.GetVerifyCodeParam>() {

    override suspend fun run(params: GetVerifyCodeParam): List<String> {
        return authRepository.getAuthType(params.email)
    }

    data class GetVerifyCodeParam(val email: String)
}