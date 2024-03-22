package flab.tickethub.auth.application.service

import flab.tickethub.auth.adaptor.`in`.AuthCommandUseCase
import flab.tickethub.support.security.TokenProvider
import flab.tickethub.auth.domain.MemberPrincipal
import flab.tickethub.auth.domain.TokenPair
import flab.tickethub.auth.domain.TokenPayload
import flab.tickethub.member.application.port.out.MemberQueryPort
import flab.tickethub.support.error.ApiException
import flab.tickethub.support.error.ErrorCode
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class AuthCommandService(
    private val memberQueryPort: MemberQueryPort,
    private val tokenProvider: TokenProvider
) : AuthCommandUseCase {

    override fun updateRefreshToken(tokenPayload: TokenPayload): TokenPair {
        val tokenPair = tokenProvider.generateTokenPair(tokenPayload)
        val memberId = tokenPair.tokenPayload.id()

        val member = findById(memberId)
        member.login(tokenPair.refreshToken)

        return tokenPair
    }

    override fun logout(memberPrincipal: MemberPrincipal) {
        val member = findById(memberPrincipal.id())
        member.logout()
    }

    private fun findById(memberId: Long) =
        memberQueryPort.findById(memberId)
            ?: throw ApiException(ErrorCode.NOT_FOUND_MEMBER)

}
