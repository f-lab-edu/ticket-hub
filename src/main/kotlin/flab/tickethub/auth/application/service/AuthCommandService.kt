package flab.tickethub.auth.application.service

import flab.tickethub.auth.adaptor.`in`.request.LoginRequest
import flab.tickethub.auth.adaptor.`in`.request.RefreshAccessTokenRequest
import flab.tickethub.auth.application.port.`in`.AuthCommandUseCase
import flab.tickethub.auth.domain.MemberPrincipal
import flab.tickethub.auth.domain.TokenPair
import flab.tickethub.member.application.port.out.MemberQueryPort
import flab.tickethub.support.error.ApiException
import flab.tickethub.support.error.ErrorCode
import flab.tickethub.support.security.TokenProvider
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class AuthCommandService(
    private val memberQueryPort: MemberQueryPort,
    private val passwordEncoder: PasswordEncoder,
    private val tokenProvider: TokenProvider
) : AuthCommandUseCase {

    override fun login(request: LoginRequest): TokenPair {
        val member = memberQueryPort.findByEmail(request.email)
            ?: throw ApiException(ErrorCode.INVALID_EMAIL_OR_PASSWORD)
        validatePassword(
            expectedPassword = request.password,
            actualPassword = member.password
        )

        return tokenProvider.generateTokenPair(member)
            .also { member.login(it.refreshToken) }
    }

    override fun refreshAccessToken(request: RefreshAccessTokenRequest): String {
        val tokenPayload = tokenProvider.validateRefreshToken(request.refreshToken)
        val member = memberQueryPort.findById(tokenPayload.id())
            ?: throw ApiException(ErrorCode.NOT_FOUND_MEMBER)
        member.validateRefreshToken(request.refreshToken)

        return tokenProvider.generateAccessToken(tokenPayload)
    }

    override fun logout(memberPrincipal: MemberPrincipal) {
        val member = findById(memberPrincipal.id())
        member.logout()
    }

    private fun validatePassword(
        expectedPassword: String,
        actualPassword: String
    ) {
        if (!passwordEncoder.matches(expectedPassword, actualPassword))
            throw ApiException(ErrorCode.INVALID_EMAIL_OR_PASSWORD)
    }

    private fun findById(memberId: Long) =
        memberQueryPort.findById(memberId)
            ?: throw ApiException(ErrorCode.NOT_FOUND_MEMBER)

}
