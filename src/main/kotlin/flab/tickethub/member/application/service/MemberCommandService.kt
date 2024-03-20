package flab.tickethub.member.application.service

import flab.tickethub.member.adaptor.`in`.request.CreateMemberRequest
import flab.tickethub.member.application.port.`in`.MemberCommandUseCase
import flab.tickethub.member.application.port.out.MemberCommandPort
import flab.tickethub.member.application.port.out.MemberQueryPort
import flab.tickethub.member.domain.Member
import flab.tickethub.support.error.ApiException
import flab.tickethub.support.error.ErrorCode
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class MemberCommandService(
    private var memberCommandPort: MemberCommandPort,
    private var memberQueryPort: MemberQueryPort,
    private var passwordEncoder: PasswordEncoder
) : MemberCommandUseCase {

    override fun create(request: CreateMemberRequest) {
        validateExistsEmail(request.email)
        val encodedPassword = passwordEncoder.encode(request.password)

        val member = Member.from(
            request = request,
            encodedPassword = encodedPassword
        )
        memberCommandPort.create(member)
    }

    private fun validateExistsEmail(email: String) {
        if (memberQueryPort.existsByEmail(email)) throw ApiException(ErrorCode.DUPLICATED_MEMBER_EMAIL)
    }

}