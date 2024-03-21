package flab.tickethub.auth.application.service

import flab.tickethub.auth.adaptor.`in`.request.LoginRequest
import flab.tickethub.auth.application.port.`in`.AuthQueryUseCase
import flab.tickethub.auth.domain.TokenPayload
import flab.tickethub.member.application.port.out.MemberQueryPort
import flab.tickethub.support.error.ApiException
import flab.tickethub.support.error.ErrorCode
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class AuthQueryService(
    private val memberQueryPort: MemberQueryPort,
    private val passwordEncoder: PasswordEncoder,
) : AuthQueryUseCase {

    override fun login(request: LoginRequest): TokenPayload {
        val member = memberQueryPort.findByEmail(request.email)
            ?: throw ApiException(ErrorCode.INVALID_EMAIL_OR_PASSWORD)

        validatePassword(
            expectedPassword = request.password,
            actualPassword = member.password
        )

        return member
    }

    private fun validatePassword(
        expectedPassword: String,
        actualPassword: String
    ) {
        if (!passwordEncoder.matches(expectedPassword, actualPassword))
            throw ApiException(ErrorCode.INVALID_EMAIL_OR_PASSWORD)
    }

}
