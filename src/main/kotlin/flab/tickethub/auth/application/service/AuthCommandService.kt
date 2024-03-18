package flab.tickethub.auth.application.service

import flab.tickethub.auth.adaptor.`in`.AuthCommandUseCase
import flab.tickethub.member.application.port.out.MemberQueryPort
import flab.tickethub.support.error.ApiException
import flab.tickethub.support.error.ErrorCode
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class AuthCommandService(
    private val memberQueryPort: MemberQueryPort
) : AuthCommandUseCase {

    override fun updateRefreshToken(
        memberId: Long,
        refreshToken: String
    ) {
        val member = memberQueryPort.findById(memberId)
            ?: throw ApiException(ErrorCode.NOT_FOUND_MEMBER)
        member.refreshToken = refreshToken
    }

}
