package flab.tickethub.member.application.service

import flab.tickethub.member.adaptor.`in`.request.CreateMemberRequest
import flab.tickethub.member.application.port.`in`.MemberCommandUseCase
import flab.tickethub.member.application.port.out.MemberCommandPort
import flab.tickethub.member.application.port.out.MemberQueryPort
import flab.tickethub.member.domain.Member
import org.springframework.stereotype.Service

@Service
class MemberCommandService(
    private var memberCommandPort: MemberCommandPort,
    private var memberQueryPort: MemberQueryPort
) : MemberCommandUseCase {

    override fun create(request: CreateMemberRequest) {
        validateExistsEmail(request.email)

        val member = Member.from(request)
        memberCommandPort.create(member)

    }

    private fun validateExistsEmail(email: String) {
        if (memberQueryPort.existsByEmail(email)) throw IllegalArgumentException("이미 존재하는 이메일입니다.")
    }

}